package pk_thongbao;

public class thongbao {
    int idUer;
    private String Ten;
    private String MoTa;
    private String Hinh;
    private  String time;


    public thongbao() {

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getIdUer() {
        return idUer;
    }

    public void setIdUer(int idUer) {
        this.idUer = idUer;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String hinh) {
        Hinh = hinh;
    }
}
