module ee.ut.mangfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens ee.ut.mangfx to javafx.fxml;
    exports ee.ut.mangfx;
}