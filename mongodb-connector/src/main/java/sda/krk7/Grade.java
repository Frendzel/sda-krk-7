package sda.krk7;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import java.io.Serializable;

/*
{
	_id: "50906d7fa3c412bb040eb892",
	student_id: 198,
	type: "homework",
	score: 17.46279901047208
}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade implements Serializable, Bson {
    Object _id;
    Integer student_id;
    String type;
    Double score;

    public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
        return new BsonDocumentWrapper(this, codecRegistry.get(Grade.class));
    }
}
