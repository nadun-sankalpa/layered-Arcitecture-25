module com.example.layeredarchitecture {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;

    opens lk.Ijse.pos to javafx.fxml;
    opens lk.Ijse.pos.controller to javafx.fxml;
    opens lk.Ijse.pos.view.tdm to javafx.base;

    exports lk.Ijse.pos;
    exports lk.Ijse.pos.controller;
}
