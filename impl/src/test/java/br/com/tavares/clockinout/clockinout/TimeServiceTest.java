package br.com.tavares.clockinout.clockinout;

import br.com.tavares.clockinout.clockinout.enuns.TimeCourseEnum;
import br.com.tavares.clockinout.clockinout.exception.ApiException;
import br.com.tavares.clockinout.clockinout.model.TimeModel;
import br.com.tavares.clockinout.clockinout.repository.TimeManagementRepository;
import br.com.tavares.clockinout.clockinout.repository.entity.TimeEntity;
import br.com.tavares.clockinout.clockinout.stub.TimeEntityStub;
import br.com.tavares.clockinout.clockinout.stub.TimeModelStub;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
class TimeServiceTest {

    @Mock
    private TimeManagementRepository timeManagementRepository;
    @InjectMocks
    private TimeService timeService;

    /**
     * Dado que o metodo [save] seja chamado...
     * Dado que seja passado um objeto TimeModel
     * Deve retornar um objeto TimeModel com id
     */

    @Test
    void deve_retornar_um_objeto_timemodel_com_id() {
        TimeModel timeModelComId = TimeModelStub.getTimeModelComId();
        TimeModel timeModel = TimeModelStub.getTimeModel();
        when(timeManagementRepository.save(any())).thenReturn(TimeEntityStub.getTimeEntity());
        TimeModel response = timeService.timeSave(timeModel);
        Assert.assertEquals(timeModelComId, response);
    }

    /**
     * Dado que o metodo [timeSave] seja chamado...
     * Dado que seja passado um objeto timeModel
     * Deve retornar uma lista de Time com o valor [10:30:00.000]
     */
    @Test
    void deve_retornar_uma_lista_de_time() {
        List<TimeEntity> timeEntityList = TimeEntityStub.getTimeEntityList();
        when(timeManagementRepository.findAllByUserIdAndAndDate(any(), any())).thenReturn(timeEntityList);
        List<LocalTime> times = timeService.findTimes(TimeModelStub.getTimeModel());
        Assert.assertEquals("10:30:00.000", times.get(0).toString());
    }

    /**
     * Dado que o metodo [addPeriod] seja chamado...
     * Dado que seja passado uma lista vazia
     * Deve retornar uma lista de Time com o valor FIRST_PERIOD_IN
     */
    @Test
    void deve_retornar_o_periodo_first_period_in() {
        List<LocalTime> localTimes = new ArrayList<>();
        TimeCourseEnum response = timeService.addPeriod(localTimes);
        Assert.assertEquals(TimeCourseEnum.FIRST_PERIOD_IN, response);
    }

    /**
     * Dado que o metodo [addPeriod] seja chamado...
     * Dado que seja passado uma lista com um time
     * Deve retornar uma lista de Time com o valor FIRST_PERIOD_OUT
     */
    @Test
    void deve_retornar_o_periodo_first_period_out() {
        List<LocalTime> localTimes = Arrays.asList(LocalTime.parse("10:30:00"));
        TimeCourseEnum response = timeService.addPeriod(localTimes);
        Assert.assertEquals(TimeCourseEnum.FIRST_PERIOD_OUT, response);
    }

    /**
     * Dado que o metodo [addPeriod] seja chamado...
     * Dado que seja passado uma lista com dois time
     * Deve retornar uma lista de Time com o valor ECOND_PERIOD_IN
     */
    @Test
    void deve_retornar_o_periodo_second_period_in() {
        List<LocalTime> localTimes = Arrays.asList(LocalTime.parse("10:30:00"), LocalTime.parse("10:30:00"));
        TimeCourseEnum response = timeService.addPeriod(localTimes);
        Assert.assertEquals(TimeCourseEnum.SECOND_PERIOD_IN, response);
    }

    /**
     * Dado que o metodo [addPeriod] seja chamado...
     * Dado que seja passado uma lista com tres time
     * Deve retornar uma lista de Time com o valor ECOND_PERIOD_OUT
     */
    @Test
    void deve_retornar_o_periodo_second_period_out() {
        List<LocalTime> localTimes = Arrays.asList
                (LocalTime.parse("10:30:00"), LocalTime.parse("11:30:00"), LocalTime.parse("12:30:00"));
        TimeCourseEnum response = timeService.addPeriod(localTimes);
        Assert.assertEquals(TimeCourseEnum.SECOND_PERIOD_OUT, response);
    }

    /**
     * Dado que o metodo [checkInterval] seja chamado...
     * Deve retornar uma DateTimeException
     */
    @Test
    void deve_retornar_uma_datetimeexception() {
        List<LocalTime> localTimes = Arrays.asList(LocalTime.parse("12:00:00"));
        TimeModel timeModel = TimeModelStub.getTimeModel();
        timeModel.setPeriod(TimeCourseEnum.SECOND_PERIOD_IN);
        timeModel.setTime("12:30");
        Assert.assertThrows(DateTimeException.class, () -> timeService.checkInterval(timeModel, localTimes));
    }

    /**
     * Dado que o metodo [checksIfTheTimeIsLessThanThePreviousTime] seja chamado...
     * Dado que seja passado uma data anterior a ultima data lançada no periodo
     * Deve retornar uma DateTimeException
     */
    @Test
    void deve_retornar_datetimeexception() {
        List<LocalTime> localTimes = Arrays.asList(LocalTime.parse("12:00:00"));
        TimeModel timeModel = TimeModelStub.getTimeModel();
        timeModel.setPeriod(TimeCourseEnum.FIRST_PERIOD_OUT);
        timeModel.setTime("11:30");
        Assert.assertThrows(DateTimeException.class, () -> timeService.checksIfTheTimeIsLessThanThePreviousTime(timeModel, localTimes));
    }

    /**
     * Dado que o metodo [checkWeekend] seja chamado...
     * Dado que seja passado uma data que seja um sabado
     * Deve retornar uma ApiException
     */
    @Test
    void deve_retornar_ApiException_quando_for_sanbado() {
        TimeModel timeModel = TimeModelStub.getTimeModel();
        timeModel.setDate("20-3-2021");
        Assert.assertThrows(ApiException.class, () -> timeService.checkWeekend(timeModel));
    }

    /**
     * Dado que o metodo [checkWeekend] seja chamado...
     * Dado que seja passado uma data que seja um domingo
     * Deve retornar uma ApiException
     */
    @Test
    void deve_retornar_ApiException_quando_for_domingo() {
        TimeModel timeModel = TimeModelStub.getTimeModel();
        timeModel.setDate("21-3-2021");
        Assert.assertThrows(ApiException.class, () -> timeService.checkWeekend(timeModel));
    }


}