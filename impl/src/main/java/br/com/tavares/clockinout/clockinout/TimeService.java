package br.com.tavares.clockinout.clockinout;

import br.com.tavares.clockinout.clockinout.enuns.TimeCourseEnum;
import br.com.tavares.clockinout.clockinout.exception.ApiException;
import br.com.tavares.clockinout.clockinout.mapper.TimeMapper;
import br.com.tavares.clockinout.clockinout.model.TimeModel;
import br.com.tavares.clockinout.clockinout.repository.TimeManagementRepository;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.util.List;
import java.util.stream.Collectors;

import static org.joda.time.format.DateTimeFormat.forPattern;

@Service
public class TimeService {
    private TimeManagementRepository timeManagementRepository;

    public TimeService(TimeManagementRepository timeManagementRepository) {
        this.timeManagementRepository = timeManagementRepository;
    }

    public TimeModel timeSave(TimeModel timeModel) {
        return TimeMapper.mapToTimeModel(timeManagementRepository.save(TimeMapper.mapToTimeEntity(timeModel)));
    }

    public List<LocalTime> findTimes(TimeModel timeModel) {
        return timeManagementRepository.findAllByUserIdAndAndDate(timeModel.getUserId(), timeModel.getDate())
                .parallelStream()
                .map(entity -> LocalTime.parse(entity.getTime()))
                .collect(Collectors.toList());
    }

    public void checkWeekend(TimeModel timeModel) {
        LocalDate.Property property = LocalDate.parse(timeModel.getDate(), forPattern("dd-MM-yyyy")).dayOfWeek();
        if (property.get() == 6 || property.get() == 7)
            throw new ApiException("You can't work at weekends.", HttpStatus.BAD_REQUEST);
    }

    public TimeCourseEnum addPeriod(List<LocalTime> localTimes) {
        if (localTimes.isEmpty())
            return TimeCourseEnum.FIRST_PERIOD_IN;
        if (localTimes.size() == 1)
            return TimeCourseEnum.FIRST_PERIOD_OUT;
        if (localTimes.size() == 2)
            return TimeCourseEnum.SECOND_PERIOD_IN;
        if (localTimes.size() == 3)
            return TimeCourseEnum.SECOND_PERIOD_OUT;
        else
            throw new ApiException("You cannot add more records today", HttpStatus.BAD_REQUEST);
    }

    public void checkInterval(TimeModel timeModel, List<LocalTime> times) {
        if (timeModel.getPeriod().equals(TimeCourseEnum.SECOND_PERIOD_IN)) {
            LocalTime lastRecord = getLastRecord(times);
            if (checksTimeInterval(timeModel, times))
                throw new DateTimeException(
                        String.format("Invalid time, the last time was made at %s . Please, insert a time after %s",
                                lastRecord.toString().substring(0, 5), lastRecord.plusHours(1).toString().substring(0, 5))
                );
        }
    }

    public void checksIfTheTimeIsLessThanThePreviousTime(TimeModel timeModel, List<LocalTime> times) {
        if (!timeModel.getPeriod().equals(TimeCourseEnum.FIRST_PERIOD_IN)) {
            if (LocalTime.parse(timeModel.getTime()).isBefore(getLastRecord(times))) {
                throw new DateTimeException("The time reported is before the last recorded time");
            }
        }
    }

    private boolean checksTimeInterval(TimeModel timeModel, List<LocalTime> times) {
        return times
                .parallelStream()
                .anyMatch(time -> LocalTime.parse(timeModel.getTime()).isBefore(time.plusHours(1)));
    }

    public LocalTime getLastRecord(List<LocalTime> times) {
        return times
                .parallelStream()
                .max(LocalTime::compareTo)
                .orElseGet(() -> LocalTime.now().minusHours(8));
    }
}
