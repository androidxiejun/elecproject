package addressdao.com.example.administrator.electronicproject;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.
/**
 * Entity mapped to table "ADDRESS".
 */
@Entity
public class Address {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String userName;

    @NotNull
    private String userMobile;

    @NotNull
    private String userAddress;

    @NotNull
    private String addressDetails;

    @Generated
    public Address() {
    }

    public Address(Long id) {
        this.id = id;
    }

    @Generated
    public Address(Long id, String userName, String userMobile, String userAddress, String addressDetails) {
        this.id = id;
        this.userName = userName;
        this.userMobile = userMobile;
        this.userAddress = userAddress;
        this.addressDetails = addressDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public String getUserName() {
        return userName;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setUserName(@NotNull String userName) {
        this.userName = userName;
    }

    @NotNull
    public String getUserMobile() {
        return userMobile;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setUserMobile(@NotNull String userMobile) {
        this.userMobile = userMobile;
    }

    @NotNull
    public String getUserAddress() {
        return userAddress;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setUserAddress(@NotNull String userAddress) {
        this.userAddress = userAddress;
    }

    @NotNull
    public String getAddressDetails() {
        return addressDetails;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setAddressDetails(@NotNull String addressDetails) {
        this.addressDetails = addressDetails;
    }

}
