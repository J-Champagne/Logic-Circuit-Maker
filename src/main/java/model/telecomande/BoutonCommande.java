package model.telecomande;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class BoutonCommande extends JButton {
    private final TypeBoutonCommandeEnum typeBouton;

    public BoutonCommande(TypeBoutonCommandeEnum type){
        this.typeBouton = type;
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(selectImage())));
        this.setIcon(imageIcon);
        this.setPreferredSize(new Dimension(35,35));
//        this.addActionListener(context);
    }

    public BoutonCommande(TypeBoutonCommandeEnum type, String texte){
        super(texte);
        this.typeBouton = type;
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(selectImage())));
        this.setIcon(imageIcon);
        this.setPreferredSize(new Dimension(120,30));
    }

    private String selectImage () {
        String image = "";
        switch (typeBouton) {
            case ENTREE:            image = "/images/entree.png";
                break;
            case SORTIE:            image = "/images/sortie.png";
                break;
            case AND:               image = "/images/andgateorange.png";
                break;
            case OR:                image = "/images/orgategreen.png";
                break;
            case PORTE_SPECIALISE:  image = "/images/portex.png";
                break;
            case NOT:               image = "/images/notgatepink.png";
                break;
            case DEFINIR:           image = "/images/definir.png";
                break;
            case RELIER:            image = "/images/link.png";
                break;
            case SUPPRIMER_ELEMENT: image = "/images/delete.png";
                break;
            case SUPPRIMER_LIEN:   image = "/images/unlink.png";
                break;
            case UNDO:              image = "/images/undo.png";
                break;
            case POWER_OFF:         image = "/images/power2.png";
                break;
            case POWER_ON:          image = "/images/power1.png";
                break;
            case NOUVEAU:           image = "/images/nouveau.png";
                break;
            case OUVRIR:            image = "/images/ouvrir.png";
                break;
            case ENREGISTRER:       image = "/images/enregistrer.png";
                break;
            case REDO:              image = "/images/redo.png";
                break;
            case CANCEL:            image = "/images/cancel.png";
                break;
            case TEXT:              image = "/images/text.png";
                break;
            case JPEG:              image = "/images/jpeg.png";
                break;
            case PNG:               image = "/images/png.png";
                break;
            case AFFICHER:          image = "/images/vue.png";
                break;

        }
        return image;
    }
}

