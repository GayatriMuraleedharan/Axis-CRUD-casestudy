import java.sql.DriverManager
//modelling class
data class users(val id:Int, val name:String,val age:Int,val email:String,val phone:String,val city:String,val state:String,val country:String,val pincode:Int)
fun main(args:Array<String>) {
    val jdbcUrl = "jdbc:mysql://localhost:3306/crud"
    val connection = DriverManager.getConnection(jdbcUrl, "root", "root")
    println(connection.isValid(0))

    //insert query-create
    val res = connection.createStatement().executeUpdate("insert into users(id,name,age,email,phone,city,state,country,pincode) values(4,'SONA',29,'mnb@gmail.com','2347895896','ERNAKULAM','KERALA','INDIA','123456')")
    if (res > 0) {
        println("Successfully inserted a record into db!!!")
    } else {
        println("Insert not successful")
    }
    
    //update table
    val res_update=connection.createStatement().executeUpdate("update users set name='Julie',email='julie@gmail.com' where id=1")
    if(res_update > 0){
        println("Successfully updated a record into db!!!")
    }
    else{
        println("updation not successful")
   }
    //delete query

    val delete_res=connection.createStatement().executeUpdate("delete from users where id=1")
    if(delete_res > 0){
        println("Successfully deleted a record from db!!!")
    }
    else{
        println("Deletion is not successful")
    }
//    //fetch the record from database
    val query = connection.prepareStatement("select *  from users")
    val result = query.executeQuery()
    val users = mutableListOf<users>()
    while (result.next()) {
        val id = result.getInt("id")
        val name = result.getString("name")
        val age = result.getInt("age")
        val email = result.getString("email")
        val phone = result.getString("phone")
        val city = result.getString("city")
        val state = result.getString("state")
        val country = result.getString("country")
        val pincode = result.getInt("pincode")
        users.add(users(id, name, age, email, phone, city, state, country, pincode))

    }
    println(users)
}