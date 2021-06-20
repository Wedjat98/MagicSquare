
// レポート第2回: Magic Square Application

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

public class MagicSquareApp extends Application {

    public static String gakuban = "19EC601"; // 学籍番号を入力すること
    public static String yourname = "サイホウリン"; // 氏名を入力すること

    // 問題の定義
    String[] tasks = {
            "81635-492", "-7215-8-4", "-3-9-1-7-", "2------1-",
    };
    int index;
    ContextMenu contextMenu = new ContextMenu();
    MenuItem[] setnum = new MenuItem[9];
    Alert alert = new Alert(Alert.AlertType.ERROR, "不正解です！");
    Alert allRight = new Alert(Alert.AlertType.INFORMATION, "正解です！");
    LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
    List<Integer> list;
    @Override
    public void start(Stage primaryStage) throws IOException {
        // プログラムを作成
        ComboBox<String> comboBox = new ComboBox<String>();
        comboBox.getItems().addAll(tasks[0], tasks[1], tasks[2], tasks[3]);
        Label label = new Label("問題選択");
        Label info = new Label("変更したいボタンで右クリックして、数値を選択してください");
        HBox hBox = new HBox(label, comboBox);
        for (int i = 0; i < setnum.length; i++) {
            setnum[i] = new MenuItem();
            setnum[i].setText(String.valueOf(i + 1));
            contextMenu.getItems().addAll(setnum[i]);
        }

        BorderPane bp = new BorderPane();
        GridPane grid = new GridPane();
        Button[] btns = new Button[9];
        grid.setPadding(new Insets(10, 20, 0, 20));
        grid.setAlignment(Pos.CENTER);
        for (int i = 0; i < btns.length; i++) {
            btns[i] = new Button();
            if (0 <= i && i < 3) {
                grid.add(btns[i], i, 0);
            } else if (3 <= i && i < 6) {
                grid.add(btns[i], i - 3, 1);
            } else if (6 <= i && i <= btns.length) {
                grid.add(btns[i], i - 6, 2);
            }
            btns[i].setAlignment(Pos.TOP_CENTER);
            btns[i].setPadding(new Insets(12));
            btns[i].setMinSize(50, 50);


        }
        Button checkButton = new Button("Check");
        checkButton.setMinSize(75, 60);
        Button resetButton = new Button("Reset");
        resetButton.setMinSize(75, 60);
        VBox vBox = new VBox(checkButton, resetButton);

        for (int j = 0; j < btns.length; j++) {
            int finalJ = j;
            btns[j].setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    for (int i = 0; i < setnum.length; i++) {
                        int finalI = i;
                        setnum[i].setOnAction(e -> {
                            btns[finalJ].setText(String.valueOf(setnum[finalI].getText()));
                        });
                    }
                }
            });
        }

        comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String old_str, String new_str) {
                index = comboBox.getSelectionModel().getSelectedIndex();
                if (list!=null&&(!linkedHashSet.isEmpty())){
                    linkedHashSet.clear();
                    list.clear();
                }
                switch (index) {
                    case 0:
                        btns[0].setText("8");
                        btns[1].setText("1");
                        btns[2].setText("6");
                        btns[3].setText("3");
                        btns[4].setText("5");
                        btns[5].setText("");
                        btns[5].setContextMenu(contextMenu);
                        btns[6].setText("4");
                        btns[7].setText("9");
                        btns[8].setText("2");
                        break;
                    case 1:
                        btns[0].setText("");
                        btns[0].setContextMenu(contextMenu);
                        btns[1].setText("7");
                        btns[2].setText("2");
                        btns[3].setText("1");
                        btns[4].setText("5");
                        btns[5].setText("");
                        btns[5].setContextMenu(contextMenu);
                        btns[6].setText("8");
                        btns[7].setText("");
                        btns[7].setContextMenu(contextMenu);
                        btns[8].setText("4");
                        break;
                    case 2:
                        btns[0].setText("");
                        btns[0].setContextMenu(contextMenu);
                        btns[1].setText("3");
                        btns[2].setText("");
                        btns[2].setContextMenu(contextMenu);
                        btns[3].setText("9");
                        btns[4].setText("");
                        btns[4].setContextMenu(contextMenu);
                        btns[5].setText("1");
                        btns[6].setText("");
                        btns[6].setContextMenu(contextMenu);
                        btns[7].setText("7");
                        btns[8].setText("");
                        btns[8].setContextMenu(contextMenu);
                        break;
                    case 3:
                        btns[0].setText("2");
                        btns[1].setText("");
                        btns[1].setContextMenu(contextMenu);
                        btns[2].setText("");
                        btns[2].setContextMenu(contextMenu);
                        btns[3].setText("");
                        btns[3].setContextMenu(contextMenu);
                        btns[4].setText("");
                        btns[4].setContextMenu(contextMenu);
                        btns[5].setText("");
                        btns[5].setContextMenu(contextMenu);
                        btns[6].setText("");
                        btns[6].setContextMenu(contextMenu);
                        btns[7].setText("1");
                        btns[8].setText("");
                        btns[8].setContextMenu(contextMenu);
                        break;
                }
            }
        });

        checkButton.setOnAction(e -> {
            for (int i = 0; i < btns.length; i++) {
                if (btns[i].getText() != null && btns[i].getText().length() != 0) {
                    linkedHashSet.add(Integer.valueOf(btns[i].getText()));
                }
            }
            list = new LinkedList<>(linkedHashSet);
            int sum=0;
            for (int i = 0; i < list.size(); i++) {
                sum=list.get(i)+sum;
            }
            if (linkedHashSet.size() ==9&&sum==45) {
                allRight.showAndWait();
                sum=0;
                list.clear();
                linkedHashSet.clear();
            } else {
                alert.showAndWait();
            }

        });
        resetButton.setOnAction(e -> {
            for (int i = 0; i < btns.length; i++) {
                btns[i].setText("");
            }
            linkedHashSet.clear();
            list.clear();
        });
        bp.setTop(hBox);
        hBox.setPadding(new Insets(10, 20, 0, 20));
        hBox.setAlignment(Pos.CENTER);
        bp.setLeft(grid);
        bp.setRight(vBox);
        vBox.setPadding(new Insets(10, 20, 0, 20));
        bp.setBottom(info);
        info.setPadding(new Insets(10, 20, 0, 20));
        info.setAlignment(Pos.CENTER);

        primaryStage.setScene(new Scene(bp, 400, 250));
        primaryStage.setTitle("MagicSquareApp");

        grid.setAlignment(Pos.TOP_CENTER);
        primaryStage.show();
    }



    public static void main(String[] args) {
        // アプリケーションを起動する
        Application.launch(args);
        System.out.println("完了--MagicSquareApp");
    }
}

/* 考察 -- 調査したこと、考慮したこと、工夫したことを記述




 */
