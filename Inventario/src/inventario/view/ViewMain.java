package inventario.view;

import inventario.logic.Products;
import inventario.logic.Vendings;
import inventario.model.Category;
import inventario.model.Product;
import inventario.model.Vending;
import inventario.utilities.PVent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Consumer;
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
import javax.swing.JOptionPane;

/**
 *
 * @author Frank Bustamante
 *
 */
public class ViewMain extends VBox {

    //INSTANCIAS
    private HBox panelOpciones;
    private GridPane panelHome;
    private final TableView<PVent> table = new TableView<>();
    private final ObservableList<PVent> data = FXCollections.observableArrayList();
    private Button btHome, btReporte, btSearch, btSave, btNew;
    private TextField txtCode, txtAmount, txtTotal;
    private Label lCodige, lName, lType, lQuantity, lAmount;
    private TableColumn colCode, colName, colType, colAmount, colActions, colPrice;

    public ViewMain() {
        launch();
    }

    private void launch()  {
        init();
        config();
        events();

        //AGREGAR ELEMENTOS
        table.setItems(data);
        table.getColumns().addAll(colCode, colName, colType, colPrice, colAmount, colActions);

        this.panelOpciones.getChildren().addAll(btHome,
                btSearch, btReporte);

        this.panelHome.addColumn(1,txtTotal, txtCode, txtAmount);
        this.panelHome.addRow(2, btNew);
        this.panelHome.addColumn(5, table);
        this.panelHome.addRow(3, btSave);

        this.getChildren().addAll(panelOpciones, panelHome);
    }

    private void init() {
        //PANELS
        panelOpciones = new HBox();
        panelHome = new GridPane();

        //BUTTONS
        btHome = new Button("Home");
        btReporte = new Button("Reportes");
        btSearch = new Button("Buscar");
        btSave = new Button("Guardar");
        btNew = new Button("Agregar");

        //TEXTFIELDS
        txtCode = new TextField();
        txtAmount = new TextField();
        txtTotal = new TextField();

        //LABELS
        lCodige = new Label("Codigo");
        lName = new Label("Nombre");
        lType = new Label("Tipo");
        lQuantity = new Label("Disponible");
        lAmount = new Label("Cantidad");

        //COLUMNS
        colCode = new TableColumn("Codigo");
        colName = new TableColumn("Nombre");
        colType = new TableColumn("Tipo");
        colAmount = new TableColumn("Cantidad");
        colActions = new TableColumn("Sup");
        colPrice = new TableColumn("Precio");

    }

    private void config() {
        //FIELDS
        txtAmount.setPromptText("Cantiad");
        txtCode.setPromptText("Codigo");
        txtTotal.setPromptText("Total");
                

        //BUTTONS
        this.btHome.setGraphic(new ImageView(new Image(
                getClass().getResourceAsStream("fot.png"),
                24, 24, false, true
        )));

        //PAELS
        this.panelOpciones.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 )");
        this.panelOpciones.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.panelHome.setBackground(new Background(new BackgroundFill(Color.AZURE, CornerRadii.EMPTY, Insets.EMPTY)));
        table.setEditable(true);

        //COLUMNS
        colType.setCellValueFactory(
                new PropertyValueFactory<>("type"));
        colCode.setCellValueFactory(
                new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        colAmount.setCellFactory(TextFieldTableCell.forTableColumn());
        colAmount.setCellValueFactory(
                new PropertyValueFactory<>("am"));
        colActions.setCellValueFactory(
                new PropertyValueFactory<>("Eliminar"));
        colPrice.setCellValueFactory(
                new PropertyValueFactory<>("price"));
    }

    private void events() {
        //TEXTFIELD
        this.txtAmount.setOnAction((t) -> {
            this.txtCode.requestFocus();
            this.txtAmount.setText("");
            this.txtCode.setText("");
        });

        this.txtCode.setOnAction((t) -> {
            this.txtAmount.requestFocus();
        });

        //BUTTON
        this.btNew.setOnAction((e) -> {
            Product p = Products.getProduct(txtCode.getText());

            if (p != null) {
                
                data.add(new PVent(Integer.parseInt(txtCode.getText()), p.getName(),
                        Double.parseDouble(p.getPrice()), Integer.parseInt(txtAmount.getText())));
                
                this.txtTotal.setText(total());
                this.txtAmount.setText("");
                this.txtCode.setText("");
                this.txtCode.requestFocus();
                
                
            }else{
               // JOptionPane.showMessageDialog(null, "Producto no encontrado");
            }
        });
        
        btSave.setOnAction((e) ->{
            Vendings.saveVending(new Vending( data, total()));
        });
        
        btHome.setOnAction((e) ->{
            Calendar calendar = Calendar.getInstance();
            String date = calendar.get(Calendar.DATE)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);
        new Vendings().getVendings(date);
        });
        //COLUMNS
        colAmount.setOnEditCommit(
                new EventHandler<CellEditEvent<PVent, String>>() {
            @Override
            public void handle(CellEditEvent<PVent, String> t) {
                ((PVent) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setAm(Integer.parseInt(t.getNewValue()));
                txtTotal.setText(total());
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
    
    private String total(){
        double total = 0;
        total = data.stream().map((p) -> Double.parseDouble(p.getAm()) *
                Double.parseDouble(p.getPrice())).reduce(total, (accumulator, _item) -> accumulator + _item);
        
        
        
        return String.valueOf(total);
    }

}
