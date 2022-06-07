package com.kivaatello.fragments2

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.kivaatello.fragments2.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class DogViewHolder(view: View): RecyclerView.ViewHolder(view){
    private val binding = ItemDogBinding.bind(view)
    /*lateinit var dialog: MyDialog*/

    fun bind(image:String, position: Int, contexto: Context){
        Picasso.get().load(image).into(binding.ivDog)
        binding.ivDog.setOnClickListener {
            val builder = AlertDialog.Builder(contexto)
            builder.setTitle("Mensaje")
                .setMessage("Quieres abrir la foto?")
                .setCancelable(true)
                .setPositiveButton("Abrir"){
                    dialogInterface, it -> abrirFoto(image, contexto)
                }
                .setNegativeButton("Cancelar"){
                    dialogInterface,it -> dialogInterface.cancel()
                }
                .show()
            /*dialog.setMessage("Quieres abrir la foto?")
                .setCancelable(true)
                .setPositiveButton("Abrir"){
                        dialogInterface, it -> abrirFoto(image, contexto)
                }
                .setNegativeButton("Cancelar"){
                        dialogInterface,it -> dialogInterface.cancel()
                }
                .show()*/
        }
    }
    
    fun abrirFoto(image: String, contexto: Context){
        val intentVerFoto: Intent = Intent(contexto, VerFoto::class.java)
        intentVerFoto.putExtra("url", image)
        contexto.startActivity(intentVerFoto)
    }

    /*fun showMyDialog(imagen: String, context: Context){
        var dialog = Dialog(context)
        var btnCancel = dialog.findViewById<Button>(R.id.btnCancelar)
        var btnAbrir = dialog.findViewById<Button>(R.id.btnAbrir)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btnCancel.setOnClickListener{
            dialog.dismiss()
        }

        btnAbrir.setOnClickListener{
            abrirFoto(imagen, context)
        }

        dialog.show()
    }*/
}