package zhangjie.validator.basic.model;

/**
 * @Author zhangjie
 * @Date 2020/9/9 21:29
 **/
public class Car {
    private String manufacturer;
    private String licensePlate;
    private int seatCount;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }
}
