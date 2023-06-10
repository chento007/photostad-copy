package co.istad.photostad.api.watermark;

import co.istad.photostad.api.json.Design;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Watermark {
    private Integer id;
    private String uuid;
    private Design editorJson;
    private Integer createdBy;
    private Timestamp createdAt;
    private Boolean isDeleted;
}
