package Utils

import Model.MyModel

interface DbServiceInterface {
    fun addNotes(myModel: MyModel)
    fun addFavourite(myModel: MyModel)
    fun deleteNotes(myModel: MyModel)
    fun updateNotes(myModel: MyModel): Int
    fun getAllNotes(): List<MyModel>
}