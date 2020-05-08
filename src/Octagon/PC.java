package Octagon;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PC {
    String modelName;
    int stock;
    double price;
    Image image;
    ImageView imageview;
    String os;
    String processor;
    String memory;
    String storage;
    String display;
    String graphics;
    String ood;
    int addedToCartCounter;

    public PC(String modelName, int stock, double price, Image image, String os, String processor, String memory,
              String storage, String display, String graphics, String ood){
        this.modelName = modelName;
        this.stock = stock;
        this.price = price;
        this.image = image;
        this.imageview = new ImageView(image);
        this.os = os;
        this.processor = processor;
        this.memory = memory;
        this.storage = storage;
        this.display = display;
        this.graphics = graphics;
        this.ood = ood;
    }



    public double getPrice() {
        return price;
    }

    public Image getImage() {
        return image;
    }

    public int getStock() {
        return stock;
    }

    public ImageView getImageview() {
        return imageview;
    }

    public String getDisplay() {
        return display;
    }

    public String getGraphics() {
        return graphics;
    }

    public String getMemory() {
        return memory;
    }

    public String getModelName() {
        return modelName;
    }

    public String getOod() {
        return ood;
    }

    public String getOs() {
        return os;
    }

    public String getProcessor() {
        return processor;
    }

    public String getStorage() {
        return storage;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //shows pc details
    //what shows up when button is clicked on HomePage
    public BorderPane showDetails(GridPane itemPane){
        DecimalFormat df = new DecimalFormat("#.##");
        Alert alert = new Alert();


        BorderPane itemInfo = new BorderPane();//where every element is managed and arranged

        GridPane itemSpecs = new GridPane();//where laptop information is stored


        Pane pane = new Pane();//used to manage image
        Image itemImage = getImage();
        ImageView itemImageview = new ImageView(itemImage);

        itemImageview.setFitWidth(300);
        itemImageview.setFitHeight(300);
        itemInfo.setCenter(pane);

        //item information
        Label specsTitle = new Label ("Specifications"); Label stockPresent = new Label("Stock ( "+getStock()+" pcs )");
        Label modelLabel = new Label("Model Name"); Label modelName = new Label(getModelName());
        Label osLabel = new Label("Operating System"); Label osName = new Label(getOs());
        Label processorLabel = new Label("Processor"); Label processName = new Label(getProcessor());
        Label memoryLabel = new Label("Memory");    Label memoryName = new Label(getMemory());
        Label storageLabel = new Label ("Storage"); Label storageName = new Label(getStorage());
        Label displayLabel = new Label("Display");  Label displayName = new Label(getDisplay());
        Label graphicsLabel = new Label("Graphics"); Label graphicsName = new Label(getGraphics());
        Label oodLabel = new Label("Optical Drive"); Label oodName = new Label(getOod());

        itemSpecs.add(specsTitle,0,0); itemSpecs.add(stockPresent,1,0);
        itemSpecs.add(modelLabel,0,1);  itemSpecs.add(modelName,1,1);
        itemSpecs.add(osLabel,0,2);     itemSpecs.add(osName,1,2);
        itemSpecs.add(processorLabel,0,3);  itemSpecs.add(processName,1,3);
        itemSpecs.add(memoryLabel,0,4);     itemSpecs.add(memoryName,1,4);
        itemSpecs.add(storageLabel,0,5);    itemSpecs.add(storageName,1,5);
        itemSpecs.add(displayLabel,0,6);    itemSpecs.add(displayName,1,6);
        itemSpecs.add(graphicsLabel,0,7);   itemSpecs.add(graphicsName,1,7);
        itemSpecs.add(oodLabel,0,8);    itemSpecs.add(oodName,1,8);


        itemInfo.getStylesheets().add(getClass().getResource("css/gridLines.css").toExternalForm());
        itemInfo.setRight(itemSpecs);
        Label infoTitle = new Label(modelName.getText());
        infoTitle.setStyle("-fx-font-size:17px; -fx-text-fill:white;-fx-font-weight:bold;");
        itemInfo.setTop(infoTitle);

        //arranges items
        itemSpecs.setTranslateX(350);
        itemSpecs.setTranslateY(40);
        itemSpecs.setGridLinesVisible(true);
        itemInfo.setPadding(new Insets(0,0,0,50));

        //items at the bottom
        final int[] itemQuantity = {0};
        final double[] pricetoPay = {getPrice()};
        HBox checkOut = new HBox();
        Button addToCart = new Button("Add to Cart");
        Button plus = new Button("+");
        TextField quantity = new TextField("" + itemQuantity[0]);
        quantity.setEditable(false);
        quantity.setPrefSize(50,25);
        Button minus = new Button("-");
        Label priceLabel = new Label ("Price (Php)");
        TextField price = new TextField(""+(pricetoPay[0]*itemQuantity[0]));
        priceLabel.setPadding(new Insets(0,0,0,20));
        priceLabel.setStyle("-fx-font-size:15px;");
        price.setEditable(false);
        checkOut.setLayoutY(300);

        addToCart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (itemQuantity[0] == 0) {
                    alert.popUpMessage("Can't add nothing.", "Zero quantity.");
                } else if (getStock() == 0) {
                    alert.popUpMessage("Sorry,item not available.", "Out of stocks");
                } else {
                    addedToCartCounter++;
                    ArrayList<Double> priceQuantity = new ArrayList<Double>();
                    priceQuantity.add((double) itemQuantity[0]);
                    priceQuantity.add(pricetoPay[0]);


                    //checks if item has been added to cart again, then adds price and quantity else, just inputs values.
                    if (Main.itemsBought.containsKey(getModelName())) {
                        ArrayList<Double> addValues = Main.itemsBought.get(getModelName());
                        Main.itemsBought.get(getModelName()).set(0, (addValues.get(0) + priceQuantity.get(0)));
                        Main.itemsBought.get(getModelName()).set(1, (addValues.get(1) + priceQuantity.get(1)));
                        setStock((int) (getStock() - priceQuantity.get(0)));
                    } else {
                        Main.itemsBought.put(getModelName(), priceQuantity);
                        setStock((int) (getStock() - priceQuantity.get(0)));
                    }

                    Main.Home.setCenter(itemPane);
                    Pane pane = new Pane();
                    Button remove = new Button("X");
                    ImageView newImage = new ImageView(getImage());
                    newImage.setFitWidth(150);
                    newImage.setFitHeight(150);
                    newImage.setY(80);
                    Label name = new Label(getModelName());
                    Label cartQuantity = new Label("" + itemQuantity[0]);
                    Label cartPrice = new Label("" + pricetoPay[0]);
                    remove.setLayoutY(178);
                    remove.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            //removes items from cart and adjusts info on itemsBought accordingly
                            Main.cartItems.getChildren().removeAll(pane, cartQuantity, cartPrice);
                            if (addedToCartCounter > 1) {
                                setStock((int) (getStock() + priceQuantity.get(0)));
                                ArrayList<Double> minusValues = Main.itemsBought.get(getModelName());
                                Main.itemsBought.get(getModelName()).set(0, (minusValues.get(0) - priceQuantity.get(0)));
                                Main.itemsBought.get(getModelName()).set(1, (minusValues.get(1) - priceQuantity.get(1)));
                                addedToCartCounter--;
                            } else {
                                setStock((int) (getStock() + priceQuantity.get(0)));
                                Main.itemsBought.remove(getModelName());
                                addedToCartCounter--;
                            }

                            //adjust information on cart after item is removed
                            if (!(Main.itemsBought.isEmpty())) {
                                int totalItemsAdded = 0;
                                double totalPriceAdded = 0;
                                for (ArrayList statistic : Main.itemsBought.values()) {
                                    totalItemsAdded += Double.parseDouble(statistic.get(0).toString());
                                    totalPriceAdded += Double.parseDouble(statistic.get(1).toString());
                                }
                                Main.itemsTextfield.setText("" + totalItemsAdded);
                                Main.totalTextfield.setText("" + totalPriceAdded);
                                Main.vatTextField.setText("" + df.format((totalPriceAdded * 1.12)));
                            } else {
                                Main.itemsTextfield.setText("0");
                                Main.totalTextfield.setText("0");
                                Main.vatTextField.setText("0");
                            }
                            Main.Home.setCenter(Main.cartScroll);
                        }
                    });
                    //sends all data to cart for display
                    pane.getChildren().addAll(newImage, name, remove);
                    Main.cartItems.add(pane, 0, Main.addToCartCounter);
                    Main.cartItems.add(cartQuantity, 1, Main.addToCartCounter);
                    Main.cartItems.add(cartPrice, 2, Main.addToCartCounter);
                    Main.addToCartCounter++;
                    alert.popUpMessage("Item added to cart.", "Successful");
                }
            }
        });

        plus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(itemQuantity[0] < getStock()){
                    itemQuantity[0] +=1;
                    quantity.setText(""+ itemQuantity[0]);
                    pricetoPay[0] = getPrice()*itemQuantity[0];
                    price.setText(""+df.format(pricetoPay[0]));
                } else{
                    itemQuantity[0] = getStock();
                    quantity.setText(""+ itemQuantity[0]);
                    pricetoPay[0] = getPrice()*itemQuantity[0];
                    price.setText(""+df.format(pricetoPay[0]));
                    quantity.setText(""+ itemQuantity[0]);
                }
            }
        });


        minus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(itemQuantity[0] >= 1 ){
                    itemQuantity[0]--;
                    quantity.setText(""+ itemQuantity[0]);
                    pricetoPay[0] = getPrice()*itemQuantity[0];
                    price.setText(""+df.format(pricetoPay[0]));
                } else{
                    itemQuantity[0] = 0;
                    quantity.setText(""+ itemQuantity[0]);
                }
            }
        });
        checkOut.getChildren().addAll(addToCart,minus,quantity,plus,priceLabel,price);
        pane.getChildren().addAll(itemImageview,itemSpecs,checkOut);
        return itemInfo;
    }

    //Shows link(name) and image of item in the homepage.
    public void addPane(GridPane itemPane, int x, int y){
        Pane pane = new Pane();
        pane.setStyle("-fx-pref-height:175px; -fx-pref-width:250px;");
        pane.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                new BorderWidths(1))));
        Hyperlink link = new Hyperlink(getModelName());
        link.setAlignment(Pos.TOP_CENTER);
        link.setStyle("-fx-text-alignment:center");
        ImageView image = getImageview();
        image.setFitHeight(130);
        image.setFitWidth(130);
        image.setX(40);
        image.setY(50);
        //clicking on link shows item details
        link.setOnAction(e -> {
            Main.Home.setCenter(showDetails(itemPane));
        });
        link.setLayoutX(70);
        pane.getChildren().addAll(image,link);
        pane.setPadding(new Insets(0,0,0,0));
        itemPane.add(pane,x,y);
    }
}
