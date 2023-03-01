package com.b.alkhatib.cloudlab_firebase

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.b.alkhatib.cloudlab_firebase.databinding.ActivityAddInformationBinding
import com.b.alkhatib.cloudlab_firebase.databinding.ActivityGetInformationBinding
import com.google.firebase.firestore.FirebaseFirestore


class GetInformationActivity : AppCompatActivity() {
    lateinit var binding: ActivityGetInformationBinding
    lateinit var db: FirebaseFirestore
    lateinit var usersData: ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        usersData = ArrayList()

        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Uploading...")
        progressDialog.setMessage("Please wait while the data is being uploaded")
        progressDialog.show()

        db.collection("users")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    progressDialog.dismiss()

                    for (document in task.result) {
                        val name = document.get("name").toString()
                        val number = document.get("number").toString()
                        val address = document.get("address").toString()
                        usersData.add(User(name, number, address))
                    }

                } else {
                    Toast.makeText(this, "Failed, try again", Toast.LENGTH_SHORT).show()
                }
            }

        val userInfoAdapter= UserAdapter(this, usersData)
        binding.


    }
}