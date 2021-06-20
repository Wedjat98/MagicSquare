import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MonthlyCalendar extends Application {
	String[] header = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };
	GridPane grid = new GridPane();
	ComboBox<Integer> year = new ComboBox<>();
	ComboBox<Integer> month = new ComboBox<>();
	Button submit = new Button();

	@Override
	public void start(Stage primaryStage) {
		// Create the Top layout
		Label yLabel = new Label("年");
		for (int y = 2022; y >= 2001; y--)
			year.getItems().add(y);
		Label mLabel = new Label("月");
		for (int m = 12; m >= 1; m--)
			month.getItems().add(m);
		submit.setText("表示");
		submit.setOnAction(e -> {
			if (year.getValue() != null && month.getValue() != null) {
				reset();
				showHeader();
				showBody();
			}
		});
		HBox hb = new HBox(10, year, yLabel, month, mLabel, submit);
		hb.setPadding(new Insets(10, 20, 0, 20));
		hb.setAlignment(Pos.CENTER);

		// Create the Center layout（課題9-1の確認用）
		grid.setGridLinesVisible(true);
		showHeader();
		// Create the BorderPane
		BorderPane bp = new BorderPane();
		bp.setTop(hb);
		bp.setCenter(grid);
		// Create the scene and Show on the stage
		primaryStage.setScene(new Scene(bp, 400, 280));
		primaryStage.setTitle("GridPane Test1");
		primaryStage.show();
	}

	// Reset the Calendar
	public void reset() {
		grid.getChildren().clear();
		grid.getColumnConstraints().clear();
	}

	// Show the Calendar header
	public void showHeader() {
		// 課題9-1のコードを記述
		Label[] label = new Label[header.length];
		ColumnConstraints column = new ColumnConstraints();
		column.setPercentWidth(10);
		for (int i = 0; i < header.length; i++) {
			label[i] = new Label(header[i]);
			grid.add(label[i], i, 0);
			label[i].setAlignment(Pos.TOP_CENTER);
		}
		grid.getColumnConstraints().addAll(column, column, column, column, column, column, column);
		grid.setAlignment(Pos.TOP_CENTER);
	}

	// Show the Calendar body
	public void showBody() {
		// 課題9-2のコードを記述
		Label[] labels = new Label[40];
		ColumnConstraints column = new ColumnConstraints();
		column.setPercentWidth(10);
		LocalDate endDate = LocalDate.of(year.getValue(), month.getValue(), 1);
		LocalDate startDate = LocalDate.of(1900, 1, 1);
		long sumDay = startDate.until(endDate, ChronoUnit.DAYS);
		long FirstDay = (sumDay + 1) % 7;
		long everyday = sumDay + 1;
		int monthDay = endDate.lengthOfMonth();


		for (int i = 0; i < labels.length; i++) {
			labels[i] = new Label();
			labels[i].setAlignment(Pos.TOP_CENTER);
		}
		try {
			for (int i = 0; i < header.length; i++) {
				grid.add(labels[i], i, 1);
				labels[i].setAlignment(Pos.TOP_CENTER);
			}
			for (int i = 0; i < header.length; i++) {
				grid.add(labels[i + header.length], i, 2);
				labels[i].setAlignment(Pos.TOP_CENTER);
			}
			for (int i = 0; i < header.length; i++) {
				grid.add(labels[i + header.length * 2], i, 3);
				labels[i].setAlignment(Pos.TOP_CENTER);
			}
			for (int i = 0; i < header.length; i++) {
				grid.add(labels[i + header.length * 3], i, 4);
				labels[i].setAlignment(Pos.TOP_CENTER);
			}
			for (int i = 0; i < header.length; i++) {
				grid.add(labels[i + header.length * 4], i, 5);
				labels[i].setAlignment(Pos.TOP_CENTER);
			}
			for (int i = 0; i < header.length; i++) {
				grid.add(labels[i + header.length * 5], i, 6);
				labels[i].setAlignment(Pos.TOP_CENTER);
			}
			for (int i = 0; i < header.length; i++) {
				grid.add(labels[i + header.length * 6], i, 7);
				labels[i].setAlignment(Pos.TOP_CENTER);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
//			System.out.println("ArrayIndexOutOfBoundsException!");　
		}
		for (int i = 0; i <= monthDay-1; i++) {
			if (everyday % 7 == 6) {
				labels[(int) (i + FirstDay)].setText("" + (i+1));
			} else {
				labels[(int) (i + FirstDay)].setText("" + (i+1));
			}
			everyday++;
		}

	}

	public static void main(String[] args) {
		Application.launch(args);
		System.out.println("完了--MonthlyCalendar");
	}
}
