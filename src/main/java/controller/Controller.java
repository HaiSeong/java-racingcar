package controller;

import domain.CarFactory;
import domain.Cars;
import domain.RacingCount;
import domain.RacingRule;
import dto.CarStatus;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Cars cars = initCars();
        RacingCount racingCount = inputView.inputRacingCount();

        List<String> winners = racing(cars, racingCount);

        outputView.printWinners(winners);
    }

    private Cars initCars() {
        List<String> carNames = inputView.inputCars();

        return CarFactory.generateCars(carNames);
    }

    private List<String> racing(Cars cars, RacingCount racingCount) {
        outputView.printResultMessageTitle();
        for (int i = 0; i < racingCount.getCount(); i++) {
            List<CarStatus> raceResult = cars.race();
            outputView.printRacingResult(raceResult);
        }

        return getWinners(cars);
    }

    private List<String> getWinners(Cars cars) {
        RacingRule racingRule = new RacingRule();

        return racingRule.getWinners(cars)
                .stream()
                .map(car -> car.getStatus().name())
                .toList();
    }
}
