package POJO.post;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;

    public boolean equalsObj(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Post)) {
            return false;
        }
        Post tds = (Post) obj;
        return userId == tds.userId && id == tds.id && title.equals(tds.title) && body.equals(tds.body);
    }
}
