package test;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;

public class CassandraApp {

    private static final Logger LOG = LoggerFactory.getLogger(CassandraApp.class);

//    private static Cluster cluster;
//    private static Session session;
//    private static String host="10.0.1.5";
//    private static int port=9160;

    public static void main(String[] args) {


        ApplicationContext context = new ClassPathXmlApplicationContext("test/context.xml");
//        PersonRepository repo = context.getBean(PersonRepository.class);

//        Session session = context.getBean(Session.class);

        Cluster cluster = context.getBean(Cluster.class);

//        repo.save(new Person("qqqqq32","test3ee", 23));

//        for(Person person: repo.findAll()){
//            System.out.println("czczc "+person);
//        }
//
//        session.close();
        cluster.close();

//        System.exit(0);



//        try {
//
////            Collection<InetSocketAddress> connection=new ArrayList<InetSocketAddress>();
////            connection.add(new InetSocketAddress(host,port));
////            cluster = Cluster.builder().addContactPointsWithPorts(connection).build();
//            cluster = Cluster.builder().addContactPoints(InetAddress.getByName(host)).build();
//
//            session = cluster.connect("mykeyspace");
//
//            CassandraOperations cassandraOps = new CassandraTemplate(session);
//
//            cassandraOps.insert(new Person("1234567890", "David", 40));
//
//            //Select s = QueryBuilder.select().from("person");
//            //s.where(QueryBuilder.eq("id", "1234567890"));
//
//            System.out.println(cassandraOps.selectAll(Person.class));
//
////            LOG.info(cassandraOps.queryForObject(s, Person.class));
//
//            cassandraOps.truncate("person");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}