package org.vc.visionarycroftingmvc.voDTO;

public class ProductVo {
    private String ref;
    private String name;
    private String prixMax;
    private String prixMin;
    private String category;

    public ProductVo() {
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrixMax() {
        return prixMax;
    }

    public void setPrixMax(String prixMax) {
        this.prixMax = prixMax;
    }

    public String getPrixMin() {
        return prixMin;
    }

    public void setPrixMin(String prixMin) {
        this.prixMin = prixMin;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
