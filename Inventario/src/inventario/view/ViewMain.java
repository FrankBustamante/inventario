package inventario.view;

import inventario.model.Category;
import inventario.model.Product;
import inventario.utilities.PVent;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author Frank Bustamante
 *
 */
public class ViewMain extends VBox {

    //iNSTANCIAS
    private HBox panelOpciones;
    private GridPane panelHome;
    private final TableView<PVent> table = new TableView<>();
    private final ObservableList<PVent> data = FXCollections.observableArrayList();
    private Button btHome, btReporte, btSearch, btCategory, btSave, btNew;
    private TextField txtCode, txtAmount;
    private Label lCodige, lName, lType, lQuantity, lAmount;
    private TableColumn colCode, colName, colType, colAmount, colActions, colPrice;

    public ViewMain() {
        getConfig();
    }

    private void getConfig() {
        //INICIAR ELEMENTOS
        panelOpciones = new HBox();
        panelHome = new GridPane();
        
        btHome = new Button("Home");
        btReporte = new Button("Reportes");
        btSearch = new Button("Buscar");
        btCategory = new Button("Categorias");
        btSave = new Button("Guardar");
        btNew = new Button("Agregar");

        txtCode = new TextField();
        txtAmount = new TextField();

        lCodige = new Label("Codigo");
        lName = new Label("Nombre");
        lType = new Label("Tipo");
        lQuantity = new Label("Disponible");
        lAmount = new Label("Cantidad");

        colCode = new TableColumn("Codigo");
        colName = new TableColumn("Nombre");
        colType = new TableColumn("Tipo");
        colAmount = new TableColumn("Cantidad");
        colActions = new TableColumn("Sup");
        colPrice = new TableColumn("Precio");

        //AGREGAR ELEMENTOS
        table.setItems(data);
        table.getColumns().addAll(colCode, colName, colType,colPrice, colAmount, colActions);

        this.panelOpciones.getChildren().addAll(btHome,
                btSearch, btReporte, btCategory);

        this.panelHome.addColumn(1, txtCode, txtAmount);
        this.panelHome.addRow(2, btNew);
        this.panelHome.addColumn(5, table);

        this.getChildren().addAll(panelOpciones, panelHome);

        events();
        configElements();
    }

    private void configElements() {

        //configurar elementos
        this.btHome.setGraphic(new ImageView(new Image(
                getClass().getResourceAsStream("fot.png"),
                24, 24, false, true
        )));
        this.panelOpciones.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 )");
        this.panelOpciones.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.panelHome.setBackground(new Background(new BackgroundFill(Color.AZURE, CornerRadii.EMPTY, Insets.EMPTY)));
        table.setEditable(true);

        colType.setCellValueFactory(
                new PropertyValueFactory<PVent, String>("type"));
        colCode.setCellValueFactory(
                new PropertyValueFactory<PVent, String>("code"));
        colName.setCellValueFactory(
                new PropertyValueFactory<PVent, String>("name"));
        colAmount.setCellFactory(TextFieldTableCell.forTableColumn());
        colAmount.setCellValueFactory(
                new PropertyValueFactory<PVent, String>("amount"));
        colActions.setCellValueFactory(
                new PropertyValueFactory<Button,Button>("Eliminar"));
        colPrice.setCellValueFactory(
                new PropertyValueFactory<PVent,Double>("price"));
    }

    private void events() {
        
        this.txtAmount.setOnAction((t) -> {
            this.txtCode.requestFocus();
            this.txtAmount.setText("");
            this.txtCode.setText("");
        });

        this.txtCode.setOnAction((t) -> {
            this.txtAmount.requestFocus();
        });

        this.btNew.setOnAction((e) -> {
            System.out.println(txtCode.getText()+ txtAmount.getText());
            data.add(new PVent(txtCode.getText(), txtAmount.getText()));
            this.txtAmount.setText("");
            this.txtCode.setText("");
            this.txtCode.requestFocus();
        });

        colAmount.setOnEditCommit(
                new EventHandler<CellEditEvent<PVent, String>>() {
            @Override
            public void handle(CellEditEvent<PVent, String> t) {
                ((PVent) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setAmount(t.getNewValue());
                System.out.println("nuevo Valor: " + t.getNewValue());
                
            }
        }
        );
        
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
    }

}
