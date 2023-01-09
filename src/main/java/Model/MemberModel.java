package Model;

import db.DbConnection;
import to.Member;
import util.Crudutil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberModel {
    public static boolean addMember(Member member) throws ClassNotFoundException, SQLException {
        return Crudutil.execute("INSERT INTO member VALUES(?,?,?,?)",
                member.getId(),
                member.getName(),
                member.getAddress(),
                member.getContatc()
        );
    }
    public static String generateNextMeberid() throws SQLException, ClassNotFoundException {
        String sql = "SELECT id FROM member ORDER BY id DESC LIMIT 1";
        ResultSet result = Crudutil.execute(sql);

        if (result.next()) {
            return generateNextOrderId(result.getString(1));
        }
        return generateNextOrderId(result.getString(null));
    }

    private static String generateNextOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("M0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "M0" + id;
        }
        return "O001";

    }
    public static ArrayList<Member> getAllCustomer() throws ClassNotFoundException, SQLException{
        ResultSet rst = DbConnection.getInstance().getConnection()
                .createStatement().executeQuery("SELECT * FROM member");

        ArrayList<Member>customerList=new ArrayList<>();

        while(rst.next()){
            Member member = new Member(rst.getString(1),rst.getString(2),rst.getString(3),
                    rst.getString(4));
            customerList.add(member);
        }
        return customerList;
    }
    public static boolean deleteMember(String id) throws ClassNotFoundException, SQLException {
        return  DbConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From member where id ='"+id+"'")>0;
    }
    public static ArrayList<String> loadMemberIds() throws SQLException, ClassNotFoundException {
        String sql = "SELECT id FROM member";
        ResultSet result = Crudutil.execute(sql);

        ArrayList<String> idList = new ArrayList<>();

        while (result.next()) {
            idList.add(result.getString(1));
        }
        return idList;
    }
}
