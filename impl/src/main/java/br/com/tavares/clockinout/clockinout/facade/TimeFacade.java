package br.com.tavares.clockinout.clockinout.facade;

import br.com.tavares.clockinout.clockinout.TimeService;
import br.com.tavares.clockinout.clockinout.model.TimeModel;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TimeFacade {

    private TimeService timeService;

    public TimeFacade(TimeService timeService) {
        this.timeService = timeService;
    }

    public TimeModel timeSave(TimeModel timeModel) {
        List<LocalTime> times = timeService.findTimes(timeModel);
        timeModel.setPeriod(timeService.addPeriod(times));
        timeService.checkWeekend(timeModel);
        timeService.checkInterval(timeModel, times);
        timeService.checksIfTheTimeIsLessThanThePreviousTime(timeModel, times);
        return timeService.timeSave(timeModel);
    }

}
