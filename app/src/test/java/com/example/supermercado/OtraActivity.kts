fun getCategories() {
    val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
    val call = service.getCategoryList()
    call.enqueue(object : Callback<Category> {
        override fun onFailure(call: Call<Category>, t: Throwable) {

            Toast.makeText(this@SplashActivity, "Something went wrong", Toast.LENGTH_SHORT)
                .show()
        }

        override fun onResponse(
            call: Call<Category>,
            response: Response<Category>
        ) {

            for (arr in response.body()!!.categorieitems!!) {
                getMeal(arr.strcategory)
            }
            insertDataIntoRoomDb(response.body())
        }

    })
}


fun getMeal(categoryName: String) {
    val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
    val call = service.getMealList(categoryName)
    call.enqueue(object : Callback<Meal> {
        override fun onFailure(call: Call<Meal>, t: Throwable) {

            loader.visibility = View.INVISIBLE
            Toast.makeText(this@SplashActivity, "Something went wrong", Toast.LENGTH_SHORT)
                .show()
        }

        override fun onResponse(
            call: Call<Meal>,
            response: Response<Meal>
        ) {

            insertMealDataIntoRoomDb(categoryName, response.body())
        }

    })
}