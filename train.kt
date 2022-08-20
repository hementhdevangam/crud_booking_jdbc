import java.sql.DriverManager
data class TrainDetails(val train_id:Int,val train_no:Int,val train_name:String,val source:String, val destination:String,val start_date_time:String, val arrival_time:String)

fun main(args: Array<String>) {
    val jdbcUrl = "jdbc:mysql://localhost:3306/train"
    val connection = DriverManager.getConnection(jdbcUrl,"root","your bd password")
    println(connection.isValid(0))

    //Insert query
    val res_insert = connection.createStatement().executeUpdate("insert into train(train_no,train_name,source,destination,start_date_time,arrival_time)values(12090,'vaskodigama exp','bangalore','goa','25-8-22 2:00pm','26-8-22 6:00pm')")
    if(res_insert>0){
        println("Record inserted seccessfully")
    }else{
        println("unsble to insert record")
    }

    //update query
    val res_update = connection.createStatement().executeUpdate("update train set  train_name = 'yalahanka exp' where train_id=2")
    if(res_update>0){
        println("Record updated successfully")
    }else{
        println("Unable to update record")
    }

    //delete query
    val res_delete =  connection.createStatement().executeUpdate("delete from train where train_id=3")
    if(res_delete>0){
        println("Record deleted successfully")
    }else{
        println("Unable to delete record")
    }

    //Select query
    val query = connection.prepareStatement("select * from train")
    val result = query.executeQuery()
    val train = mutableListOf<TrainDetails>()

    while (result.next()){
        val train_id = result.getInt("train_id")
        val train_no = result.getInt("train_no")
        val train_name = result.getString("train_name")
        val source = result.getString("source")
        val destination = result.getString("destination")
        val start_date_time = result.getString("start_date_time")
        val arrival_time = result.getString("arrival_time")

        train.add(TrainDetails(train_id,train_no,train_name,source, destination, start_date_time, arrival_time))
    }
    println(train)
}