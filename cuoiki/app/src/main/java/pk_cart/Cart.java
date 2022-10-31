package pk_cart;

public class Cart {
    int idSP;
    String tenSP;
    long GiaSP;
    String HinhAnh;
    int SoLuong;

    public Cart(int idSP, String tenSP, long giaSP, String hinhAnh, int soLuong) {
        this.idSP = idSP;
        this.tenSP = tenSP;
        GiaSP = giaSP;
        HinhAnh = hinhAnh;
        SoLuong = soLuong;
    }

    public int getIdSP() {
        return idSP;
    }

    public void setIdSP(int idSP) {
        this.idSP = idSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public long getGiaSP() {
        return GiaSP;
    }

    public void setGiaSP(long giaSP) {
        GiaSP = giaSP;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }
}

