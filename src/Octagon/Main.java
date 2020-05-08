package Octagon;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {
    DecimalFormat df = new DecimalFormat("#.##");
    static HashMap <String, ArrayList<Double>> itemsBought = new HashMap<String, ArrayList<Double>>();//stores details about all items bought
    Alert alert = new Alert(); // for popup-messages
    userData userdata = new userData(); // stores buyer's details

    //items for HomePage
    static BorderPane Home = new BorderPane(); //where everything is added/ manages how elements are shown
    static GridPane laptopsPane = new GridPane(); // shows list of laptops for purchase
    static GridPane desktopPane = new GridPane();

    //Items used when Check Cart is Clicked
    static ScrollPane cartScroll = new ScrollPane();//wrapper, makes adding unlimited items possible and visible
    static BorderPane cart = new BorderPane(); // separates elements
    static GridPane cartItems = new GridPane(); // where items are stored

    //Items that shows summary of transaction(cart)
    GridPane summaryItems = new GridPane();

    //items that shows total items(cart)
    HBox itemsNo = new HBox();
    Label totalItems = new Label("Total Items");
    static TextField itemsTextfield = new TextField();

    //items that shows totalPrice(cart)
    HBox totalPriceNo = new HBox();
    Label empty = new Label(""); //empty slot for adjusting Gridpane
    Label totalPriceLabel = new Label("Payment");
    static TextField totalTextfield = new TextField();

    //items that show vat(cart)
    HBox vat = new HBox();
    Label vat12 = new Label("VAT 12%: ");
    static TextField vatTextField = new TextField();

    //items used for checkout(cart)
    HBox checkOut = new HBox();
    Button creditsBtn = new Button("Enter Credits\n for Checkout");
    TextField checkOutField = new TextField();


    static ArrayList<Node> preserveNodes = new ArrayList<Node>();//Used to save certain nodes after purchase

    //Items used to make receipt
    GridPane receiptsList = new GridPane();//Where receipts stored after transaction
    int makeReceiptCounter = 0;

    static int addToCartCounter = 1; //manages how items are added to GridPane(laptopPane)



    //Laptop objects
    PC Aspire = new PC("ACER ASPIRE 5 \nA515-54G-55S1",100,33185.56,
            new Image("file:///C:/Users/Optiplex/IdeaProjects/Sem2PrelimProg/src/Octagon/images/Laptops/Acer-Aspire.png"),
            "Windows 10 Home","CORE I5-10210U (1.6GHZ)","4GB","1TB + 256GB SSD","15.6 FHD / 1920x1080 Screen Resolution",
            "GeForce® MX250 (up to 2GB DDR5 Dedicated )","No OOD");

    PC E5 = new PC("ACER ASPIRE \nE5-476G-33YQ/\nGREY/ CORE I3   ",100,30999,
            new Image("file:///C:/Users/Optiplex/IdeaProjects/Sem2PrelimProg/src/Octagon/images/Laptops/E5.png"),
            "Windows 10 Home","Intel Core i3-7130U Processor (3M Cache, 2.70 GHz)","4GB","2TB SATA HDD","14\" HD 1366 x 768 resolution",
            "NVIDIA GeForce MX130 \nwith 2 GB of dedicated GDDR5 VRAM","DVD Super Multi Burner");

    PC Helius = new PC("ACER PREDATOR \nHELIOS \n300 G3-571-76HW/",100,66999,
            new Image("file:///C:/Users/Optiplex/IdeaProjects/Sem2PrelimProg/src/Octagon/images/Laptops/Helius.png"),
            "Windows 10 Home","Intel Core i7-7700HQ","8 GB of DDR4","1 TB 2.5-inch 5400 RPM","15.6\" display with IPS (In-Plane Switching) technology",
            "NVIDIA® GeForce® GTX 1060 \n6 GB dedicated GDDR5 VRAM","No OOD");

    PC Capt = new PC("ACER ASPIRE 6 \nCAPTAIN AMERICA \nA615-51G-      \t",100,38688,
            new Image("file:///C:/Users/Optiplex/IdeaProjects/Sem2PrelimProg/src/Octagon/images/Laptops/Capt.png"),
            "Windows 10 Home","Intel® Core™ i5-8250U processor","4GB DDR4","1TB + 256GB SSD","15.6\" IPS Full HD",
            "NVIDIA® GeForce® MX150 \nwith 2 GB of dedicated GDDR5 VRAM","No OOD");

    PC Ironman = new PC("ACER SWIFT 3 \nIRON MAN\n/SF314-53G-550F/",100,49999,
            new Image("file:///C:/Users/Optiplex/IdeaProjects/Sem2PrelimProg/src/Octagon/images/Laptops/Ironman.png"),
            "Windows 10 Home", "Intel® Core™ i5-8250U processor","4GB DDR3","256GB SSD","14.0\" display IPS Full HD",
                    "NVIDIA® GeForce® MX150 \nwith 2 GB of dedicated GDDR5 VRAM","No OOD");

    PC Scar = new PC("ASUS ROG \nGL503VD-FY093T\n/CORE I7-7700HQ",100,66326.94,
            new Image("file:///C:/Users/Optiplex/IdeaProjects/Sem2PrelimProg/src/Octagon/images/Laptops/Scar.png"),
            "Windows 10 Home","Intel® Core™ i7-7700HQ (2.8GHZ)","8GB DDR4","1TB HDD + 128GB SSD",
            "15.6-inch Full HD (1920 by 1080) TN panel, \n60Hz Anti-Glare Panel, 5ms, 72% NTSC",
            "NVIDIA® GeForce® GTX 1050 \n4GB GDDR5 VRAM","No OOD");

    //desktop Objects
    PC P00 = new PC("HP DT SLIM \n290-P0046D\t",100,33900, new Image("file:///C:/Users/Optiplex/IdeaProjects/Sem2PrelimProg/src/Octagon/images/DesktopPC/290-A0004D1.png"),
            "WINDOWS 10","CORE I3-8100 (3.6GHZ)","4GB","1TB","19.5 HP 20KD (F02HP20KDK)","RADEON 520 2GB",
            "DVDRW");

    PC A00 = new PC("HP DT SLIM \n290-A0004D\t",100,24900, new Image("file:///C:/Users/Optiplex/IdeaProjects/Sem2PrelimProg/src/Octagon/images/DesktopPC/290-A0004D1.png"),
            "WINDOWS 10","PQC J5005 (1.5GHZ)","4GB","500GB","18.5\" HP 19KA (F02HP19KAK)","RADEON 520 2GB",
            "DVDRW");

    PC TC7 = new PC("ACER ASPIRE \nTC730-J42\t",100, 21999 , new Image("file:///C:/Users/Optiplex/IdeaProjects/Sem2PrelimProg/src/Octagon/images/DesktopPC/ACER-ASPIRE.png"),
            "WINDOWS 10","PENTIUM J4205 (1.5GHZ)","2GB","500GB","18.5\" EB192 (F02ACEB192)","RADEON 520 2GB",
            "DVDRW");

    PC Lenovo = new PC("LENOVO \nDT 510-15 \n(90HU0026PH)\t",100,29995, new Image("file:///C:/Users/Optiplex/IdeaProjects/Sem2PrelimProg/src/Octagon/images/DesktopPC/lenovo.png"),
            "WINDOWS 10","CORE I3-8100 (3.6GHZ)","4GB","1TB","21.5\" (F02LI2215S)","GT 730 2GB DDR5",
            "DVDRW");

    PC Tuf = new PC("ASUS TUF DT \nFX10CP-PH006T\t",100,43995, new Image("file:///C:/Users/Optiplex/IdeaProjects/Sem2PrelimProg/src/Octagon/images/DesktopPC/asus-tuf.png"),
            "WINDOWS 10","CORE I5-8400 (2.8GHZ)","4GB DDR4 2666","1TB","21.5\" (F02LI2215S)","NV GT1030 2GB",
            "DVDRW");

    PC Tc860 = new PC("ACER ASPIRE DT \nTC860-I5-21\t",100,46999, new Image("file:///C:/Users/Optiplex/IdeaProjects/Sem2PrelimProg/src/Octagon/images/DesktopPC/290-A0004D1.png"),
            "WINDOWS 10","CORE I5-8400 (2.8GHZ)","8GB DDR4 2666","1TB","21.5\" R221 (F02ACRR221)","NV GT1030 2GB",
            "DVDRW");



    public void HardReset(){
        Aspire.addedToCartCounter = 0;
        E5.addedToCartCounter = 0;
        Capt.addedToCartCounter = 0;
        Ironman.addedToCartCounter = 0;
        Scar.addedToCartCounter = 0;
        Helius.addedToCartCounter = 0;

        Tc860.addedToCartCounter = 0;
        TC7.addedToCartCounter = 0;
        Tuf.addedToCartCounter = 0;
        Lenovo.addedToCartCounter = 0;
        P00.addedToCartCounter = 0;
        A00.addedToCartCounter = 0;

        addToCartCounter = 1;
        cartItems.getChildren().clear();
        cartItems.getChildren().addAll(preserveNodes);
        itemsBought.clear();
    }






    @Override
    public void start(Stage primaryStage) throws Exception{
        Image logo = new Image("file:///C:/Users/Optiplex/IdeaProjects/Sem2PrelimProg/src/Octagon/images/octLogo.png");

        //Cart Gang
        cartScroll.getStylesheets().add(getClass().getResource("css/gridLines.css").toExternalForm());
        cartScroll.setId("CartContents");

        Label ItemLabel = new Label("Items");
        Label QuantityLabel = new Label("Quantity");
        Label PriceLabel = new Label("Price");

        //prevents labels from getting deleted after purchase completed
        preserveNodes.add(0, ItemLabel);
        preserveNodes.add(1,QuantityLabel);
        preserveNodes.add(2,PriceLabel);

        //adds items to cartItems GridPane
        cartItems.add(ItemLabel,0,0);
        cartItems.add(QuantityLabel,1,0);
        cartItems.add(PriceLabel,2,0);
        cartItems.setAlignment(Pos.TOP_CENTER);
        cartItems.getColumnConstraints().add(new ColumnConstraints(350)); // column 0 is 350 wide
        cartItems.getColumnConstraints().add(new ColumnConstraints(236.5)); // column 2 is 236.5 wide
        cartItems.getColumnConstraints().add(new ColumnConstraints(236.5)); // column 3 is 236.5 wide

        //adds GridPane(cartItems) to BorderPane
        cart.setCenter(cartItems);


        //summary items gang
        summaryItems.setId("Summary");

        summaryItems.getColumnConstraints().add(new ColumnConstraints(350)); // column 0 is 350 wide
        summaryItems.getColumnConstraints().add(new ColumnConstraints(236.5)); // column 1 is 236.5 wide
        summaryItems.getColumnConstraints().add(new ColumnConstraints(236.5)); // column 2 is 236.5 wide

        //edits contents of totalPriceNo(HBox) part of summaryItems
        totalTextfield.setEditable(false);
        totalTextfield.setPrefSize(100,25);
        totalPriceNo.getChildren().addAll(totalPriceLabel,totalTextfield);

        //edits contents of itemsNo(HBox) part of summaryItems
        itemsTextfield.setEditable(false);
        itemsTextfield.setPrefSize(50,25);
        itemsNo.getChildren().addAll(totalItems,itemsTextfield);

        //edits contents of vat(HBox) summaryItems
        vatTextField.setEditable(false);
        vatTextField.setPrefSize(100,25);
        vat.getChildren().addAll(vat12,vatTextField);

        //makes creditsBtn (for checkout) part of summaryItems functional
        creditsBtn.setOnAction(e -> {
            try {
                userdata.setCredits(Double.parseDouble(checkOutField.getText()));
                if(userdata.getCredits() < Double.parseDouble(vatTextField.getText())){
                    alert.popUpMessage("You don't have enough money to buy.", "Lacking credits");
                }else if(itemsBought.isEmpty()){
                    alert.popUpMessage("Add items to cart first.","Cart Empty.");
                }else{
                    double change = userdata.getCredits() - Double.parseDouble(vatTextField.getText());

                    //creates button object after every transaction containing username and date
                    Button receipt = new Button("User:"+userdata.getUsername()+" Cashier:"+userdata.getCashierName()+" "+java.time.LocalDate.now()+"");

                    //button contains a TextArea
                    TextArea Receipt = new TextArea();
                    String receiptItems = "\t\t\t\t\t\t\tOctagon Computer Superstore\n\t\t\t\t\t\t\t\tDate : "+java.time.LocalDate.now()+"\n" +
                            "\t\t\t\t\t\t\t\tCashier Name : "+userdata.getCashierName()+"\n" +
                            "\t\t\t\t\t\t\t\tBuyer Name : " +userdata.getUsername()+"\n\n\n\nItem\t\t\t\t\t\t\t\tQuantity\t\t\t\t\t\t\t\t\tPrice \n\n";

                    for(String itemBought: itemsBought.keySet()){ //iterates over all items in all itemsBought
                        receiptItems += itemBought +"\t\t\t\t\t\t" + itemsBought.get(itemBought).get(0) +
                                "\t\t\t\t\t\t\t\t\t"+ itemsBought.get(itemBought).get(1) + "\n\n";
                    }
                    int totalItemsAdded =0;
                    double totalPriceAdded =0;
                    for(ArrayList statistic : itemsBought.values()){
                        totalItemsAdded += Double.parseDouble(statistic.get(0).toString());
                        totalPriceAdded += Double.parseDouble(statistic.get(1).toString());
                    }
                    receiptItems +="\t\t\t\t\t\t\ttotal items : "+totalItemsAdded+"\t\t\t\t\t\t\ttotal price : "+totalPriceAdded+"\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  VAT 12% : "+df.format(totalPriceAdded*1.12) +"\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tYour Credits : "+df.format(userdata.getCredits())+"\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tChange : "+df.format(change);
                    Receipt.setText(receiptItems);

                    receiptsList.add(receipt,0,makeReceiptCounter); //adds receipt to receiptLists

                    //shows receipt when button clicked
                    receipt.setOnAction(w ->{
                        Home.setCenter(Receipt);
                    });
                    makeReceiptCounter ++; //manages how receipts are added to receiptsList(GridPane)

                    userdata.setCredits(userdata.getCredits() - Double.parseDouble(vatTextField.getText()));

                    HardReset();//resets all itemsBought and changes to objects

                    Home.setCenter(laptopsPane); //goes back to home

                    checkOutField.setText(""+df.format(userdata.getCredits())); //shows remaining credits when going to cart again

                    alert.popUpMessage("Items bought.", "Success");
                }
            }catch (Exception g){
                alert.popUpMessage("Please enter a number.", "Invalid Characters");
            }
        });

        checkOutField.setPrefHeight(40);
        checkOut.getChildren().addAll(creditsBtn,checkOutField);

        //adds all items to summaryItems(GridPane)
        summaryItems.add(empty,0,0);
        summaryItems.add(itemsNo,1,0);
        summaryItems.add(totalPriceNo,2,0);
        summaryItems.add(checkOut,1,1); summaryItems.add(vat,2,1);

        cart.setBottom(summaryItems);

        cartScroll.setContent(cart);//adds to Scrollpane allowing view of unlimited items added


        //home Edit
        Home.setStyle("-fx-background-color:tomato;");
        Home.setPrefWidth(950);
        Home.setPrefHeight(500);

        //laptopsPane Gang
        //Home setCenterasd
        Aspire.addPane(laptopsPane,0,0);
        Helius.addPane(laptopsPane,1,0);
        Capt.addPane(laptopsPane,2,0);
        E5.addPane(laptopsPane,0,1);
        Scar.addPane(laptopsPane,1,1);
        Ironman.addPane(laptopsPane,2,1);


        //endLaptops Gang

        //desktop PC Gang
        P00.addPane(desktopPane,0,0);
        Tc860.addPane(desktopPane,1,0);
        Lenovo.addPane(desktopPane,2,0);
        Tuf.addPane(desktopPane,0,1);
        TC7.addPane(desktopPane,1,1);
        A00.addPane(desktopPane,2,1);



        //Home setcenter > laptopsPane GridPane edit
        //What is loaded on startup
        Home.setCenter(laptopsPane);
        laptopsPane.getStylesheets().add(getClass().getResource("css/styles.css").toExternalForm());
        laptopsPane.setVgap(10);
        laptopsPane.setHgap(10);

        desktopPane.getStylesheets().add(getClass().getResource("css/styles.css").toExternalForm());
        desktopPane.setVgap(10);
        desktopPane.setHgap(10);

        //Home Options setLeft items
        VBox Options = new VBox();
        Button laptopBtn = new Button("Laptop List");
        Button desktopBtn = new Button("DesktopPC");
        Button cartBtn = new Button("Check Cart");
        Button receiptsBtn = new Button("All Receipt");
        Button logOutBtn = new Button("Logout Me");

        Options.getChildren().addAll(laptopBtn,desktopBtn,cartBtn,receiptsBtn,logOutBtn);
        Home.setLeft(Options);

        Options.setSpacing(10);



        // home setTop(display logo)
        ImageView homeLogo = new ImageView(logo);
        homeLogo.setFitHeight(50);
        homeLogo.setFitWidth(50);
        homeLogo.setPreserveRatio(true);
        Label homeOctagon = new Label("OCTAGON", homeLogo);
        Home.setTop(homeOctagon);
        BorderPane.setAlignment(homeOctagon,Pos.CENTER);


        //logIn Components
        GridPane logIn = new GridPane();
        ImageView octLogo = new ImageView(logo);
        Label Octagon = new Label("OCTAGON", octLogo);
        Octagon.setStyle("-fx-font-size:40px; -fx-text-fill:white; -fx-font-weight:bold;");
        Label Welcome = new Label("Welcome to Octagon! \n What do you want us to call you?");
        Welcome.setStyle("-fx-text-fill:white; -fx-text-alignment:center;-fx-font-size:20px;");

        TextField user = new TextField();
        TextField cashierName = new TextField();
        user.setPromptText("Name");
        cashierName.setPromptText("Cashier's name.");
        Button confirmBtn = new Button("Confirm");

        logIn.setVgap(10);
        logIn.setHgap(10);
        logIn.setPadding(new Insets(75,100,100,100));

        logIn.add(Octagon,1,1);
        logIn.add(Welcome,1,2);
        logIn.add(user,1,3);
        logIn.add(cashierName,1,4);
        logIn.add(confirmBtn,1,5);

        logIn.setStyle("-fx-background-color:tomato; -fx-text-fill:white");
        Scene loginScene = new Scene(logIn);

        Scene homeScene = new Scene(Home);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(loginScene);
        primaryStage.show();

        confirmBtn.setOnAction(e ->{
            if(user.getText().trim().isEmpty() || cashierName.getText().trim().isEmpty()){
                alert.popUpMessage("Please enter name(s).","Field not filled.");
            }else{
                userdata.setUsername(user.getText());
                userdata.setCashierName(cashierName.getText());
                user.setText("");
                cashierName.setText("");
                Home.setCenter(laptopsPane);
                primaryStage.setScene(homeScene);
            }
        });

        logOutBtn.setOnAction(e -> {
            userdata.setCredits(0);
            checkOutField.setText("0");
            HardReset();
            primaryStage.setScene(loginScene);
        });
        receiptsBtn.setOnAction(e ->{
            Home.setCenter(receiptsList);
        });

        cartBtn.setOnAction(e -> {
            if(!(itemsBought.isEmpty())){
                int totalItemsAdded =0;
                double totalPriceAdded =0;
                for(ArrayList statistic : itemsBought.values()){
                    totalItemsAdded += Double.parseDouble(statistic.get(0).toString());
                    totalPriceAdded += Double.parseDouble(statistic.get(1).toString());
                }
                itemsTextfield.setText(""+totalItemsAdded);
                totalTextfield.setText(""+totalPriceAdded);
                vatTextField.setText(""+df.format((totalPriceAdded*1.12)));
            }else{
                itemsTextfield.setText("0");
                totalTextfield.setText("0");
                vatTextField.setText("0");
            }
            Home.setCenter(cartScroll);
        });

        laptopBtn.setOnAction( e -> {
            Home.setCenter(laptopsPane);
        });
        desktopBtn.setOnAction(e ->{
            Home.setCenter(desktopPane);
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
