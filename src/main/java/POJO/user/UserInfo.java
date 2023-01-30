package POJO.user;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Integer id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public boolean equalsObj(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UserInfo)) {
            return false;
        }
        UserInfo tds = (UserInfo) obj;
        return id.equals(tds.id) && name.equals(tds.name) && username.equals(tds.username) && email.equals(tds.email)
                && address.equals(tds.address) && phone.equals(tds.phone) && website.equals(tds.website) && company
                .equals(tds.company);
    }
}