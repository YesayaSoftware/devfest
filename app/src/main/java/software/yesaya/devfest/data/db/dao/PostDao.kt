package software.yesaya.devfest.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import software.yesaya.devfest.data.db.modal.PostEntry

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg posts : PostEntry)

    @Update
    fun update(postEntry: PostEntry)

    @Query("SELECT * FROM posts")
    fun selectAll(): LiveData<List<PostEntry>>

    @Query("SELECT * FROM posts ORDER BY id DESC LIMIT 1")
    fun getPost() : PostEntry

    @Query("DELETE FROM posts WHERE id = :id")
    fun delete(id: Int)
}