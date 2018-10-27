package inventario.view;

import inventario.Inventario;
import inventario.logic.Products;
import inventario.logic.Vendings;
import inventario.model.Product;
import inventario.model.Vending;
import inventario.utilities.PVent;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author Frank Bustamante
 *
 */
public class ViewMain extends Stage {

    private TextField txtAmount, txtCode, txtTotal;

    private Pane root, paneMain;

    private VBox paneTop;

    private HBox paneSec;

    private Button btNew, btSave;

    private MenuBar mnBar;

    private Menu mnFile, mnHelp;

    private MenuItem imExit, imSearch, imA;

    private TableColumn<PVent, String> colCode, colName, colType, colPrice, colAmount;

    private final TableView<PVent> table = new TableView<>();

    private final ObservableList<PVent> data = FXCollections.observableArrayList();

    private Alert alert;

    public ViewMain(Stage stage) {
        init();

        stage.setMaxHeight(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
        stage.setMaxWidth(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
        stage.setMinHeight(500);
        stage.setMinWidth(900);
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight(), Color.AQUA);

        stage.setScene(scene);
        scene.getStylesheets().add("/inventario/style/MainStyle.css");
        stage.setResizable(true);
        stage.setTitle("ventas");
        stage.show();
        scene.setOnMouseMoved((e) -> {
            styles();
        });
        styles();

    }

    private void init() {
        //BUTTON
        btNew = new Button("Agregar");
        btSave = new Button("Guardar");

        //TEXTFIELD
        txtAmount = new TextField();
        txtCode = new TextField();
        txtTotal = new TextField();

        //COLUMN
        colAmount = new TableColumn<>("Cantidad");
        colCode = new TableColumn<>("Codigo");
        colName = new TableColumn<>("Nombre");
        colPrice = new TableColumn<>("Precio");
        colType = new TableColumn<>("Tipo");

        //MENU
        imExit = new MenuItem("Salir");
        imSearch = new MenuItem("Buscar");
        imA = new MenuItem("Ajustar");
        mnFile = new Menu("Archivo");
        mnHelp = new Menu("Ayuda", null, imSearch, imA);
        mnBar = new MenuBar(mnFile, mnHelp);

        //PANE
        paneMain = new Pane();
        root = new Pane();
        paneTop = new VBox(10);
        paneSec = new HBox();

        table.setItems(data);
        confg();
        event();
    }

    private void confg() {
        //ID
        root.setId("root");
        paneMain.setId("paneMain");
        table.setEditable(true);
        txtAmount.setId("txtAm");
        txtTotal.setId("txtTotal");
        btSave.setId("btSave");
        txtCode.setId("textf");
        paneTop.setId("paneTop");
        paneSec.setId("paneSec");

        //COLUMN
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("am"));

        //MENU
        mnFile.getItems().addAll(imExit);

        //EDIT
        colCode.setCellFactory(TextFieldTableCell.forTableColumn());
        colAmount.setCellFactory(TextFieldTableCell.forTableColumn());

        //PROMPTTEX
        txtAmount.setPromptText("Cantidad");
        txtCode.setPromptText("Codigo");
        txtTotal.setPromptText("Total");
        txtTotal.setDisable(false);
        txtTotal.setEditable(false);
        

        //ADD
        table.getColumns().addAll(colCode, colName, colType, colPrice, colAmount);
        root.getChildren().addAll(mnBar, paneMain);
        paneMain.getChildren().addAll(paneTop, table);
        paneTop.getChildren().addAll(txtTotal, paneSec);
        paneSec.getChildren().addAll(txtCode, txtAmount, btNew, btSave);
    }

    private void event() {
        table.setOnKeyPressed((e) -> {
            if (e.getCode().getName().equals("Delete") && table.getSelectionModel().getSelectedIndex() >= 0) {
                data.remove(table.getSelectionModel().getSelectedIndex());
                txtTotal.setText(total());
            }
        });

        imExit.setOnAction((e) -> {
            new LoginView((Stage) btNew.getScene().getWindow());
        });

        btNew.setOnAction((e) -> {
            addProduct(txtCode.getText());
        });

        imA.setOnAction((e) -> {
            paneTop.setMaxWidth(((Stage) btNew.getScene().getWindow()).getWidth());
            paneTop.setMinWidth(((Stage) btNew.getScene().getWindow()).getWidth());
            styles();
        });

        btSave.setOnAction((e) -> {
            if (!data.isEmpty()) {
                if (Vendings.saveVending(new Vending(data, total()), data)) {
                    clearAll();
                }
            }else{
                alert("Error al procesar la Venta", "No hay datos", 1);
            }
        });

        colCode.setOnEditCommit(
            new EventHandler<CellEditEvent<PVent, String>>() {
                @Override
                public void handle(CellEditEvent<PVent, String> t) {
                    Product p = getProduct(t.getNewValue());
                    if (p != null) {
                        data.remove(t.getTablePosition().getRow());
                        data.add((t.getTablePosition().getRow()), new PVent(Integer.parseInt(p.getCode()), p.getName(),
                                Double.parseDouble(p.getPrice()), 0));
                    }else{
                        alert("Error al agregar el Producto", "El codigo ingresado esta herrado",1);
                        t.consume();
                    }
                    //((PVent) t.getTableView().getItems().get(
                    //      t.getTablePosition().getRow())).setCode(t.getNewValue());
                    table.refresh();
                    txtTotal.setText(total());
                }
            }
        );
        
        colAmount.setOnEditCommit(
            new EventHandler<CellEditEvent<PVent, String>>() {
                @Override
                public void handle(CellEditEvent<PVent, String> t) {
                    Product p = getProduct(t.getRowValue().getCode());
                    if(Integer.parseInt(t.getNewValue()) <= Integer.parseInt(p.getQuantity())){
                        ((PVent) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setAm(Integer.parseInt(t.getNewValue()));
                        data.get(t.getTablePosition().getRow()).setAm(Integer.parseInt(t.getNewValue()));
                    }else{
                        alert("Error al agregar un producto", "Existen " + p.getQuantity() + " Unidades disponibles.", 1);
                        clear();
                    }
                    table.refresh();
                    txtTotal.setText(total());
                }
            }
        );
        
        txtCode.setOnAction((e)->{
            txtAmount.requestFocus();
        });
        txtAmount.setOnAction((e)->{
            if (!txtAmount.getText().isEmpty() && !txtAmount.getText().replaceAll(" ", "").equals("") ) {
                txtCode.requestFocus();    
            }
        });
    }

    private void styles() {
        double width = ((Stage) btNew.getScene().getWindow()).getWidth();
        double height = ((Stage) btNew.getScene().getWindow()).getHeight();
        double windowW = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;

        txtCode.setStyle(
                "-fx-min-width: 149;"
                + "-fx-max-width: 149;"
                + "-fx-translate-y: 55;"
                + "-fx-translate-x: " + (width * 0.02) + ";"
                + "-fx-min-height:29;"
                + "-fx-max-height:29;"
                + "-fx-background-radius:10;"
                + "-fx-font-size:18;"
        );

        txtTotal.setStyle(
                "-fx-min-width: " + (width * 0.9) + ";"
                + "-fx-max-width: 149;"
                + "-fx-translate-y: 34;"
                + "-fx-translate-x: " + (width * 0.02) + ";"
                + "-fx-min-height:33;"
                + "-fx-max-height:33;"
                + "-fx-background-radius:10;"
                + "-fx-background-color:#f36;"
                + "-fx-font-size:18;"
                + "-fx-alignment: TOP_RIGHT;"
                        + "-fx-text-fill:white;"
                        + "-fx-translate-z:12;"
        );

        txtAmount.setStyle(
                "-fx-min-width: 149;"
                + "-fx-max-width: 149;"
                + "-fx-translate-y: 55;"
                + "-fx-translate-x: " + (width * 0.06) + ";"
                + "-fx-min-height:30;"
                + "-fx-max-height:30;"
                + "-fx-background-radius:10;"
                + "-fx-font-size:18;"
        );

        btNew.setStyle(
                "-fx-min-width: 149;"
                + "-fx-max-width: 149;"
                + "-fx-translate-y: 55;"
                + "-fx-translate-x: " + (width * 0.22) + ";"
                + "-fx-min-height:30;"
                + "-fx-max-height:30;"
                + "-fx-background-radius:0 10 0 10;"
                + "-fx-font-size:15;"
                + "-fx-alignment: TOP_CENTER"
        );

        btSave.setStyle(
                "-fx-min-width: 149;"
                + "-fx-max-width: 149;"
                + "-fx-translate-y: 55;"
                + "-fx-translate-x: " + (width * 0.25) + ";"
                + "-fx-min-height:30;"
                + "-fx-max-height:30;"
                + "-fx-background-radius:10;"
                + "-fx-font-size:15;"
                + "-fx-alignment: TOP_CENTER"
        );

        paneMain.setStyle(
                "-fx-min-width:" + width + ";"
                + "-fx-min-height:" + height + ";"
                + "-fx-translate-x: 0;"
                + "-fx-translate-Y: 25;"
                + "-fx-padding:5;"
        );

        paneTop.setStyle(
                "-fx-min-width:" + width + ";"
                + "-fx-max-width:" + width + ";"
        );

        table.setStyle(
                "-fx-min-width:" + width + ";"
                + "-fx-min-height:" + height + ";"
                + ""
        );

        mnBar.setStyle(
                "-fx-min-width:" + java.awt.Toolkit.getDefaultToolkit().getScreenSize().width + ";"
                + "-fx-translate-x:0;"
                + "-fx-translate-y:0;"
        );

        root.setStyle("-fx-background-color:rgba( 0, 2, 44,1);");
        colName.setPrefWidth((width * 0.4));
        colCode.setPrefWidth(width * 0.13);
        colType.setPrefWidth(width * 0.18);
        colPrice.setPrefWidth(width * 0.15);
        colAmount.setPrefWidth(width * 0.09);
    }

    private boolean addProduct(String code) {
        if (!code.equals("")) {
            Product p = getProduct(code);

            if (p != null) {
                double am = Double.parseDouble(txtAmount.getText());
                int index = inData(code);
                 if (index != -1) {
                    
                    am += Double.parseDouble(data.get(index).getAm());
                     System.out.println(am);
                }
                if (Double.parseDouble(p.getQuantity()) >= am) {
                    if (index != -1){
                        data.get(index).setAm((int)am);
                        table.getItems().get(index).setAm((int)am);
                        table.refresh();
                    }else{
                        data.add(new PVent(Integer.parseInt(txtCode.getText()), p.getName(),
                                Double.parseDouble(p.getPrice()), Integer.parseInt(txtAmount.getText())));

                        
                    }
                    txtTotal.setText(total());
                        clear();
                        txtCode.requestFocus();
                    return true;
                } else {
                    clear();
                    alert("Error al agregar un producto", "Existen " + p.getQuantity() + " Unidades disponibles.", 1);
                }
            } else {
                alert("Error al agregar un producto", "Codigo erroneo...", 0);
            }
        } else {
            alert("Error al agregar un producto", "Codigo vacio", 1);
        }

        return false;
    }

    private Product getProduct(String code) {
        return Products.getProduct(code);
    }

    private int inData(String code){
        for (PVent p : data) {
            if(p.getCode().equals(code)){
                return data.indexOf(p);
            }
        }
        
        
        return -1;
    }

    /*
    
        btHome.setOnAction((e) -> {
            Calendar calendar = Calendar.getInstance();
            String date = calendar.get(Calendar.DATE) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.YEAR);
            new Vendings().getVendings(date);
        });

     this.txtAmount.setOnAction((t) -> {
            this.txtCode.requestFocus();
            this.txtAmount.setText("");
            this.txtCode.setText("");
        });

        this.txtCode.setOnAction((t) -> {
            this.txtAmount.requestFocus();
        });
     colCode.setOnEditCommit(
                new EventHandler<CellEditEvent<PVent, String>>() {
            @Override
            public void handle(CellEditEvent<PVent, String> t) {
                ((PVent) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setCode(t.getNewValue());
                System.out.println("nuevo Valor: " + t.getNewValue());
            }
        }
        );
     */
    @FXML
    private void editPrice(ActionEvent event) {
        new EventHandler<CellEditEvent<PVent, String>>() {
            @Override
            public void handle(CellEditEvent<PVent, String> t) {
                ((PVent) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setAm(Integer.parseInt(t.getNewValue()));
                txtTotal.setText(total());
            }
        };
    }

    private String total() {
        double total = 0;
        total = data.stream().map((p) -> Double.parseDouble(p.getAm())
                * Double.parseDouble(p.getPrice())).reduce(total, (accumulator, _item) -> accumulator + _item);

        return String.valueOf(total);
    }

    private void clear() {
        txtAmount.setText("");
        txtCode.setText("");
        txtCode.requestFocus();
    }

    private void clearAll() {
        clear();
        this.txtTotal.clear();
        this.data.clear();
        table.refresh();
    }

    private void alert(String h, String c, int tp) {
        if (tp == 0) {
            alert = new Alert(Alert.AlertType.INFORMATION);
        } else if (tp == 1) {
            alert = new Alert(Alert.AlertType.WARNING);
        }
        
        alert.setTitle("Advertencia");
        alert.setHeaderText(h);
        alert.setContentText(c);
        alert.showAndWait();
    }

}
