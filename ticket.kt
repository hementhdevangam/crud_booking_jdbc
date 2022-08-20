import java.sql.DriverManager
data class TicketDetails(val id:Int,val ticket_number:Int,val passenger_id:Int,val train_id:Int, val ticket_price:Int)

fun main(args: Array<String>) {
    val jdbc = "jdbc:mysql://localhost:3306/train"
    val connection = DriverManager.getConnection(jdbc,"root","your db password")
    println(connection.isValid(0))

    //Insert query
    val res_insert = connection.createStatement().executeUpdate("Insert into ticket(id,ticket_number,passenger_id,train_id,ticket_price)values(105,20199,1,2,968)")
    if(res_insert>0){
        println("Record inserted successfully")
    }else{
        println("Unable to insert record")
    }

    //Update query

    val res_update = connection.createStatement().executeUpdate("Update ticket set ticket_price = 2200 where id = 2")
    if(res_insert>0){
        println("Record updated successfully")
    }else{
        println("Unable to update record")
    }

    //delete query

    val res_delete =  connection.createStatement().executeUpdate("delete from ticket where id=104")
    if(res_delete>0){
        println("Record deleted successfully")
    }else{
        println("Unable to delete record")
    }


    //select query
    val query = connection.prepareStatement("select * from ticket")
    val result = query.executeQuery()
    val ticket = mutableListOf<TicketDetails>()

    while (result.next()){
        val id = result.getInt("id")
        val ticket_number = result.getInt("ticket_number")
        val passenger_id =result.getInt("passenger_id")
        val train_id =result.getInt("train_id")
        val ticket_price  = result.getInt("ticket_price")
        ticket.add(TicketDetails(id,ticket_number, passenger_id, train_id, ticket_price))
    }
    println(ticket)

}