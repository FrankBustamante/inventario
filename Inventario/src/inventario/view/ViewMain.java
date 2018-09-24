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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Frank Bustamante
 *
 */
public class ViewMain extends Stage{

    private TextField txtAmount, txtCode, txtTotal;
    
    private Pane root, paneMain;
 
    private Button btNew, btSave;
    
    private MenuBar mnBar;
    
    private Menu mnFile, mnHelp;
    
    private MenuItem imExit, imSearch;

    private TableColumn<PVent,String> colCode, colName, colType, colPrice, colAmount;
    
    private final TableView<PVent> table = new TableView<>();

    private final ObservableList<PVent> data = FXCollections.observableArrayList();

   
    private Alert alert;
    

    public ViewMain(Stage stage) {
        init();
      //  stage.getScene().getStylesheets().clear();
       // stage.getScene().getStylesheets().add("/inventario/style/MainStyle.css");
        //stage.getScene().setRoot(root);
       // stage.setMaxHeight(640);
        //stage.setMaxWidth(790);
         stage.setMaxHeight(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height );
        stage.setMaxWidth(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width );
        stage.setMinHeight(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
        stage.setMinWidth(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
        Scene scene = new Scene(root, Color.AQUA);
        
        stage.setScene(scene);
        scene.getStylesheets().add("/inventario/style/MainStyle.css");
        stage.setResizable(true);
        stage.setTitle("ventas");
        stage.show();
    }
    
    private void init(){
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
        mnFile = new Menu("Archivo");
        mnHelp = new Menu("Ayuda", null, imSearch);
        mnBar = new MenuBar( mnFile, mnHelp);
        
        //PANE
        paneMain = new Pane();
        root = new Pane();
        
        table.setItems(data);
        confg();
        event();
    }
    
    private void confg(){
        //ID
        root.setId("root");
        paneMain.setId("paneMain");
        table.setEditable(true);
        txtAmount.setId("txtAm");
        txtTotal.setId("txtTotal");
        btSave.setId("btSave");
        txtCode.setId("textf");
        
        //COLUMN
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("am"));
        
        //MENU
        mnFile.getItems().addAll(imExit);
        mnBar.setLayoutX(0);
        mnBar.setLayoutX(0);
        
        //SIZE
        table.setMinWidth(Double.MAX_VALUE);
        mnBar.setMinWidth(Double.MAX_VALUE);
        //table.setMaxWidth(this.getMaxWidth());
        //paneMain.setPrefSize(this.getMaxWidth(), this.getMaxHeight());
        //paneMain.setMinSize(600, 600);
        //mnBar.setMinWidth(790);
       
        
        //ADD
        table.getColumns().addAll(colCode, colName, colType, colPrice, colAmount);
        root.getChildren().addAll(mnBar, paneMain);
        paneMain.getChildren().addAll(txtTotal, txtCode, txtAmount, btNew, btSave, table);
    }
    
    private void event(){
        imExit.setOnAction((e) ->{
           new LoginView((Stage) btNew.getScene().getWindow());
        });
        
        btNew.setOnAction((e)->{
            Product p = Products.getProduct(txtCode.getText());

            if (p != null) {
                
                if(Double.parseDouble(p.getQuantity()) >= Double.parseDouble(txtAmount.getText())){
                    data.add(new PVent(Integer.parseInt(txtCode.getText()), p.getName(),
                            Double.parseDouble(p.getPrice()), Integer.parseInt(txtAmount.getText())));

                    txtTotal.setText(total());
                    clear();
                    txtCode.requestFocus();                    
                }else{
                    alert("Error al agregar un producto", "Solo hay "+p.getQuantity()+" Unidades disponibles.", 1);
                }
            } else {
                alert("Error al agregar un producto", "Codigo erroneo...", 0);
            }
        });
        
    }
    
    @FXML
    private void eventNew(ActionEvent event){
        
    }
    
    @FXML
    private void eventSave(ActionEvent event){
        if (Vendings.saveVending(new Vending(data, total()), data)) {
            clearAll();
        }
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
    private void editPrice(ActionEvent event){
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
    }
    
    private void clearAll(){
        clear();
        this.txtTotal.setText("");
        this.data.clear();
    }
    
    private void alert(String h, String c, int tp){
        if (tp == 0) {
            alert = new Alert(Alert.AlertType.INFORMATION);
        }else if(tp == 1){
            alert = new Alert(Alert.AlertType.WARNING);
        }
        
        alert.setTitle("Advertencia");
        alert.setHeaderText(h);
        alert.setContentText(c);
        alert.showAndWait();
    }


}
