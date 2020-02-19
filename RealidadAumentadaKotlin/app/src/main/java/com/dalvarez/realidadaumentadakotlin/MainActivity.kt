package com.dalvarez.realidadaumentadakotlin

import android.content.Intent
import android.graphics.Color
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import android.view.MotionEvent
import com.google.ar.core.HitResult
import com.google.ar.core.Plane


// Plugin SceneForm Importations
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.ux.BaseArFragment
import com.google.ar.core.Anchor;

class MainActivity : AppCompatActivity(), View.OnClickListener  {

    var arFragment = ArFragment()     // Se declara como variable cambiante debido a que se le asigna un valor


    /* Este es el método de creacion de variables
    * <var/val> nombreVariable : <TipoVariable> = <Inicializacion>
    * La inicializacion de variables es obligatoria
    * */

    lateinit var cajonRenderable : ModelRenderable
    lateinit var chairRenderable : ModelRenderable
    lateinit var woodtableRenderable : ModelRenderable
    lateinit var bedRenderable : ModelRenderable
    lateinit var marbelTableRenderable : ModelRenderable

    // Los atributos de clase se deben crear e inicializar de inmediato

    private var cajon : ImageView  = findViewById(R.id.cajon)
    private var chair : ImageView  = findViewById(R.id.chair)
    private var woodTable : ImageView = findViewById(R.id.woodtable)
    private var bed : ImageView = findViewById(R.id.bed)
    private var marbelTable : ImageView = findViewById(R.id.marbeltable)

    lateinit var arrayView : MutableList <View>

    var selected : Int = 1 // default cajon (?)


    override fun onCreate(savedInstance: Bundle?){

        super.onCreate(savedInstance)
        setContentView(R.layout.activity_main)

        this.arFragment = (supportFragmentManager.findFragmentById(R.id.sceneform_ux_fragment) as ArFragment?)!!

        setArrayView()

        setClickListener()

        setupModel()

        chair = findViewById(R.id.chair)
        cajon = findViewById(R.id.cajon)
        bed = findViewById(R.id.bed)
        marbelTable = findViewById(R.id.marbeltable)
        woodTable = findViewById(R.id.woodtable)

        arFragment.setOnTapArPlaneListener(BaseArFragment.OnTapArPlaneListener(
            fun (hitResult: HitResult, _: Plane, _: MotionEvent){

                val anchor: Anchor = hitResult.createAnchor()
                val anchorNode = AnchorNode(anchor)

                anchorNode.setParent(arFragment.arSceneView.scene)

                createModel(anchorNode, selected)
            }
        ))



    }

    private fun setArrayView() {

        arrayView.add(cajon)
        arrayView.add(chair)
        arrayView.add(woodTable)
        arrayView.add(marbelTable)

    }

    private fun setClickListener(){

        for (i in  arrayView.indices){

            arrayView[i].setOnClickListener(this)
        }
    }

    private fun setupModel(){

        ModelRenderable.builder()
            .setSource(this, R.raw.cajon)
            .build().thenAccept{ renderable -> chairRenderable = renderable}
            .exceptionally{
                Toast.makeText(this, "Unnable to load cajon model",
                Toast.LENGTH_LONG).show()
                null
            }

        ModelRenderable.builder()
            .setSource(this, R.raw.chair)
            .build().thenAccept{ renderable -> chairRenderable = renderable }
            .exceptionally{
                Toast.makeText(this, "Unnable to load chair model",
                Toast.LENGTH_LONG).show()
                null
            }

        ModelRenderable.builder()
            .setSource(this, R.raw.woodtable)
            .build().thenAccept{ renderable -> woodtableRenderable = renderable }
            .exceptionally{
                Toast.makeText(this, "Unnable to load woodtable model",
                Toast.LENGTH_LONG).show()
                null
            }

        ModelRenderable.builder()
            .setSource(this, R.raw.bed)
            .build().thenAccept{ renderable -> bedRenderable = renderable }
            .exceptionally{
                Toast.makeText(this, "Unnable to load bed model",
                Toast.LENGTH_LONG).show()
                null
            }

        ModelRenderable.builder()
            .setSource(this, R.raw.marbeltable)
            .build().thenAccept{ renderable -> marbelTableRenderable = renderable }
            .exceptionally{
                Toast.makeText(this, "Unnable to load marbeltable model",
                Toast.LENGTH_LONG).show()
                null
            }
    }

    private fun createModel(anchorNode: AnchorNode, selected: Int){

        if (selected == (1)){

            var cajon : TransformableNode = TransformableNode(arFragment.transformationSystem)

            cajon.setParent(anchorNode)

            cajon.renderable = cajonRenderable

            cajon.select()

            addname(anchorNode, cajon, "Cajon")

        }

        if (selected == (2)){

            var chair : TransformableNode = TransformableNode(arFragment.transformationSystem)

            chair.setParent(anchorNode)

            chair.renderable = chairRenderable

            chair.select()

            addname(anchorNode, chair, "Silla de Computadora")

        }

        if (selected == (3)){

            var woodTable : TransformableNode = TransformableNode(arFragment.transformationSystem)

            woodTable.setParent(anchorNode)

            woodTable.renderable = woodtableRenderable

            woodTable.select()

            addname(anchorNode, woodTable, "Mesa")

        }

        if (selected == (4)){

            var bed : TransformableNode = TransformableNode(arFragment.transformationSystem)

            bed.setParent(anchorNode)

            bed.renderable = bedRenderable

            bed.select()

            addname(anchorNode, bed, "Cama Matrimonial")

        }

        if (selected == (5)){

            var marbelTable : TransformableNode = TransformableNode(arFragment.transformationSystem)

            marbelTable.setParent(anchorNode)

            marbelTable.renderable = marbelTableRenderable

            marbelTable.select()

            addname(anchorNode, marbelTable, "Mesa de Marmol")

        }


    }

    private fun addname(anchorNode: AnchorNode, model: TransformableNode, name: String){

        ViewRenderable.builder().setView(this, R.layout.name_cosas)
            .build()
            .thenAccept { viewRenderable ->
                var nameView: TransformableNode = TransformableNode(arFragment.transformationSystem)

                nameView.localPosition = Vector3(0f, model.localPosition.y + 0.5f, 0f)

                nameView.setParent(anchorNode)

                nameView.renderable = viewRenderable

                nameView.select()

                // setText

                // Casting de objetos de distintas clases con as

                var txt_name: TextView = viewRenderable.view as TextView

                txt_name.setText(name)

                // click to Text

                txt_name.setOnClickListener(View.OnClickListener {
                        anchorNode.setParent(null)
                })


            }}

    private fun setBackground(id: Int){

        for (i in  arrayView.indices){

            if (arrayView[i].id == id){

                // Seleccionar color del fondo para las cosas
                arrayView[i].setBackgroundColor(Color.parseColor("#80333639"))

            }else{

                arrayView[i].setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }

    override fun onClick(v: View) {

        if (v.id == R.id.cajon){

            selected = 1

            setBackground(v.id)

            cajon.setOnClickListener(View.OnClickListener {

                fun onClick(v: View){

                    startActivity(Intent(this@MainActivity,
                        PoppupCajon::class.java))
                }
            })
        }
        else if(v.id == R.id.chair){

            selected = 2

            setBackground(v.id)

            chair.setOnClickListener(View.OnClickListener {

                fun onClick(v: View){

                    startActivity(Intent(
                        this@MainActivity,
                        PoppupChair::class.java
                    ))
                }
            })

        }
        else if(v.id == R.id.chair){

            selected = 3

            setBackground(v.id)

            chair.setOnClickListener(View.OnClickListener {

                fun onClick(v: View){

                    startActivity(Intent(
                        this@MainActivity,
                        PoppupTable::class.java
                    ))
                }
            })

        }
        else if(v.id == R.id.chair){

            selected = 4

            setBackground(v.id)

            chair.setOnClickListener(View.OnClickListener {

                fun onClick(v: View){

                    startActivity(Intent(
                        this@MainActivity,
                        PoppupBed::class.java
                    ))
                }
            })

        }
        else if(v.id == R.id.chair){

            selected = 5

            setBackground(v.id)

            chair.setOnClickListener(View.OnClickListener {

                fun onClick(v: View){

                    startActivity(Intent(
                        this@MainActivity,
                        PoppupMarbelTable::class.java
                    ))
                }
            })

        }
    }
}
