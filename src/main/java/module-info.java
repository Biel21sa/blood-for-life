module br.com.poo.bloodforlife {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    //requires javafx.media;

    //requires net.synedra.validatorfx;
    //requires org.kordamp.bootstrapfx.core;

    exports br.com.poo.bloodforlife.main;
    opens br.com.poo.bloodforlife.main to javafx.fxml;

    exports br.com.poo.bloodforlife.viewscontroller;
    opens br.com.poo.bloodforlife.viewscontroller to javafx.fxml;

    exports br.com.poo.bloodforlife.usuarios;
    opens br.com.poo.bloodforlife.usuarios to javafx.fxml;
    exports br.com.poo.bloodforlife.controladores;
    opens br.com.poo.bloodforlife.controladores to javafx.fxml;
    exports br.com.poo.bloodforlife.doacao;
    opens br.com.poo.bloodforlife.doacao to javafx.base;
}