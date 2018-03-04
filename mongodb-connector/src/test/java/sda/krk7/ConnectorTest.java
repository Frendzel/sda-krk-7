package sda.krk7;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.BSON;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Accumulators.avg;
import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;

public class ConnectorTest {

    private static final int COLLECTION_SIZE = 801;

    @Test
    public void connect() throws Exception {
        //given
        Connector connector = new Connector();
        //when
        MongoDatabase connect = connector.connect();
        //then
        Assert.assertEquals(connect.
                getCollection("grades").count(), COLLECTION_SIZE);
    }

    @Test
    public void findAllDocument() {
        //given
        Connector connector = new Connector();
        //when
        MongoDatabase connect = connector.connect();
        //then
        FindIterable<Document> grades = connect.getCollection("grades").find();
        for (Document grade : grades) {
            System.out.println(grade);
        }

    }

    @Test
    public void findOneDocumentWhereStudentIdEquals197() {
        //given
        Connector connector = new Connector();
        //when
        MongoDatabase connect = connector.connect();
        Bson filter = new Document("student_id", 197);
        //then
        FindIterable<Document> grades = connect.getCollection("grades").find(filter);
        for (Document grade : grades) {
            System.out.println(grade);
        }
    }

    @Test
    public void findDocumentsWhereStudentIdEquals197Or198() {
        //given
        Connector connector = new Connector();
        //when
        MongoDatabase connect = connector.connect();
        Bson or =
                or(
                        eq("student_id", 197),
                        eq("student_id", 198)
                );
        Bson orOr =
                Filters.or(
                        new Document("student_id", 197),
                        new Document("student_id", 198)
                );
        //then
        FindIterable<Document> grades = connect.getCollection("grades").find(or);
        for (Document grade : grades) {
            System.out.println(grade);
        }
    }

    //    db.grades.aggregate([{$match : {}},
//    {$group: { _id: "$student_id", avg: { $avg: "$score"}}},
//    {$sort : {avg : -1}}
//])
    @Test
    public void findDocumentsWhereStudentIdGreatherThan190() {
        //given
        Connector connector = new Connector();
        //when
        MongoDatabase connect = connector.connect();
        Bson match = match(new Document());
        Bson group = group("$student_id", avg("srednia", "$score"));
        Bson sort = Aggregates.sort(new Document("srednia", -1));
        List<Bson> bsons = new ArrayList<Bson>();
        bsons.add(match);
        bsons.add(group);
        bsons.add(sort);
        AggregateIterable<Document> grades = connect.getCollection("grades").aggregate(bsons);
        //then
        for (Document grade : grades){
            System.out.println(grade);
        }
    }

}