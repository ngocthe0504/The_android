package pk_lichsuhonhang;

public class danggiao {
    String image;
    String title;
    String soluong;
    String tongtien;

    public danggiao(String image, String title, String soluong, String tongtien) {
        this.image = image;
        this.title = title;
        this.soluong = soluong;
        this.tongtien = tongtien;
    }

    public danggiao() {

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getTongTien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

}
