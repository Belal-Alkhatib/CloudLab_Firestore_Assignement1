package com.b.alkhatib.cloudlab_firebase

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.b.alkhatib.cloudlab_firebase.databinding.ActivityAddInformationBinding
import com.google.firebase.firestore.FirebaseFirestore


class AddInformationActivity : AppCompatActivity() {
    lateinit var binding:ActivityAddInformationBinding
    lateinit var db:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()



    }

    override fun onResume() {
        super.onResume()
        binding.btnSave.setOnClickListener {

            val name = binding.etName.text.toString()
            val number = binding.etNumber.text.toString()
            val address = binding.etAddress.text.toString()

            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading...")
            progressDialog.setMessage("Please wait while the data is being uploaded")
            progressDialog.show()

            if (isNotEmptyInputs(name, number, address)){
                // add your data
                val user: MutableMap<String, Any> = HashMap()
                user["name"] = name
                user["number"] = number
                user["address"] = address

                db.collection("users")
                    .add(user)
                    .addOnSuccessListener { documentReference ->
                        progressDialog.dismiss()
                        Toast.makeText(this, "Added successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Add failed, try again", Toast.LENGTH_SHORT).show()
                    }

            }else{
                progressDialog.dismiss()
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isNotEmptyInputs(name:String, number:String, address:String): Boolean =
         !(name.isNullOrEmpty() || number.isNullOrEmpty() || address.isNullOrEmpty())

}