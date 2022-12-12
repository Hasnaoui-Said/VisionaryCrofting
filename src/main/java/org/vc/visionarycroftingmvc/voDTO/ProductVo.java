package org.vc.visionarycroftingmvc.voDTO;

public class ProductVo {
    private String ref;
    private String name;
    private String prixMax;
    private String prixMin;
    private String category;
    private String url_image;

    public ProductVo() {
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
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
