package com.example.a75hardchallenge.Fragments

//import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.a75hardchallenge.R
import com.example.a75hardchallenge.RoomDB.Database.UserPreferredUIDatabase
import com.example.a75hardchallenge.RoomDB.Entities.UserPrefferedUITxt
import com.example.a75hardchallenge.databinding.CustomDialogboxBinding
import com.example.a75hardchallenge.databinding.FragmentRulesBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.io.File
import kotlin.math.round


class Rules : Fragment(R.layout.fragment_rules) {

    private val requestCameraPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // Permission granted, you can now launch the camera activity
                takePicture()
            } else {
                // Permission denied, show a message to the user
                Toast.makeText(
                    requireContext(),
                    "Camera permission denied. You cannot take pictures.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    val REQUEST_CAMERA=100

    private var _binding: FragmentRulesBinding? = null
    private val firstInitialized: Boolean = true
    private val binding get() = _binding!!
    private var isCodeExecuted = false
    private var visibilityUIIncompleteWater = 0
    private var waterProgress=0
    private var outsideWorkoutProgress=0
    private var insideWorkoutProgress=0
    private var bookProgress=0
    private var pictureProgress=0
    private var alcoholProgress=0
    private var dietProgress=0
    //for firbase authorisation
    private lateinit var firebaseAuth: FirebaseAuth
    //for database reference
    private lateinit var databaseReference: DatabaseReference
    //for storage reference
    private lateinit var storageReference:StorageReference
    private var imageCaptured:Boolean=false



    var completedOrNot:Boolean=false
    //image URI
    private lateinit var imageUri: Uri
    private lateinit var database: UserPreferredUIDatabase
    private var executedBefore: Boolean = false
    fun setDatabaseInstance(db: UserPreferredUIDatabase) {
        database = db
    }
    fun setWorkoutStatus(completed:Boolean){
        completedOrNot=completed
        if (completed==true){
            binding.completeWorkoutInsideFull.visibility=View.VISIBLE
            binding.progressWorkoutOutsideText.text="1"
        }
    }
    private fun setUiVisibility(
        maxValue: Int, selectedInt: Int?, incomplete: ImageView,
        complete: ImageView,
        half: ImageView,
        quarter: ImageView,
        seventyFive: ImageView,
    ) {
        val maxDouble = maxValue.toDouble()
        if (selectedInt == null) {
            incomplete.visibility = View.INVISIBLE
            quarter.visibility = View.INVISIBLE
            half.visibility = View.INVISIBLE
            seventyFive.visibility = View.INVISIBLE
            complete.visibility = View.INVISIBLE
        } else {
            if (maxValue == 1) {
                if (selectedInt == 0) {

                    incomplete.visibility = View.VISIBLE
                    quarter.visibility = View.INVISIBLE
                    half.visibility = View.INVISIBLE
                    seventyFive.visibility = View.INVISIBLE
                    complete.visibility = View.INVISIBLE
                } else {
                    incomplete.visibility = View.INVISIBLE
                    quarter.visibility = View.INVISIBLE
                    half.visibility = View.INVISIBLE
                    seventyFive.visibility = View.INVISIBLE
                    complete.visibility = View.VISIBLE
                }
            } else {
                if (maxValue % 2 == 0) {
                    if (selectedInt == 0) {

                        incomplete.visibility = View.VISIBLE
                        quarter.visibility = View.INVISIBLE
                        half.visibility = View.INVISIBLE
                        seventyFive.visibility = View.INVISIBLE
                        complete.visibility = View.INVISIBLE
                    } else if (selectedInt in (maxValue / 2 - 1)..(maxValue / 2 + 1)) {

                        incomplete.visibility = View.INVISIBLE
                        quarter.visibility = View.INVISIBLE
                        half.visibility = View.VISIBLE
                        seventyFive.visibility = View.INVISIBLE
                        complete.visibility = View.INVISIBLE
                    } else if (selectedInt == maxValue) {

                        incomplete.visibility = View.INVISIBLE
                        quarter.visibility = View.INVISIBLE
                        half.visibility = View.INVISIBLE
                        seventyFive.visibility = View.INVISIBLE
                        complete.visibility = View.VISIBLE
                    } else if (selectedInt.toDouble() in (((maxDouble * 0.25) - 0.5)..((maxDouble * 0.25) + 0.5))) {

                        incomplete.visibility = View.INVISIBLE
                        quarter.visibility = View.VISIBLE
                        half.visibility = View.INVISIBLE
                        seventyFive.visibility = View.INVISIBLE
                        complete.visibility = View.INVISIBLE
                    } else if (selectedInt.toDouble() in (maxDouble * 0.75 - 7.5)..(maxDouble * 0.75 + 7.5)) {

                        incomplete.visibility = View.INVISIBLE
                        quarter.visibility = View.INVISIBLE
                        half.visibility = View.INVISIBLE
                        seventyFive.visibility = View.VISIBLE
                        complete.visibility = View.INVISIBLE
                    }
                } else {
                    if (selectedInt == 0) {

                        incomplete.visibility = View.VISIBLE
                        quarter.visibility = View.INVISIBLE
                        half.visibility = View.INVISIBLE
                        seventyFive.visibility = View.INVISIBLE
                        complete.visibility = View.INVISIBLE
                    } else if (selectedInt.toDouble() in (((maxDouble / 2) - 0.5)..((maxDouble / 2) + 0.5))) {
                        //visibilityUIWater=incomplete.visibility
                        incomplete.visibility = View.INVISIBLE
                        quarter.visibility = View.INVISIBLE
                        half.visibility = View.VISIBLE
                        seventyFive.visibility = View.INVISIBLE
                        complete.visibility = View.INVISIBLE
                    } else if (selectedInt == maxValue) {

                        incomplete.visibility = View.INVISIBLE
                        quarter.visibility = View.INVISIBLE
                        half.visibility = View.INVISIBLE
                        seventyFive.visibility = View.INVISIBLE
                        complete.visibility = View.VISIBLE
                    } else if (selectedInt.toDouble() in (((maxDouble * 0.25) - 0.5)..((maxDouble * 0.25) + 0.5))) {

                        incomplete.visibility = View.INVISIBLE
                        quarter.visibility = View.VISIBLE
                        half.visibility = View.INVISIBLE
                        seventyFive.visibility = View.INVISIBLE
                        complete.visibility = View.INVISIBLE
                    } else if (selectedInt.toDouble() in (((maxDouble * 0.75) - 7.5)..((maxDouble * 0.75) + 7.5))) {

                        incomplete.visibility = View.INVISIBLE
                        quarter.visibility = View.INVISIBLE
                        half.visibility = View.INVISIBLE
                        seventyFive.visibility = View.VISIBLE
                        complete.visibility = View.INVISIBLE
                    }
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        isCodeExecuted = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        setWorkoutStatus(completedOrNot)
        if (!isCodeExecuted) {
            // Your code that needs to be executed only once
            // when the fragment is created or resumed
            // ...
            database.getUserPrefferedUIDao().getUIwtrText().observe(
                viewLifecycleOwner, Observer {
                    val size = it.size

                    toggleProgressBar(0,0,0,0,0,0,0)

                    if (it.isEmpty()) {
                        binding.progressMealText.text = "0"
                        binding.progressBookpageText.text = "0"
                        binding.progressMealText.text = "0"
                        binding.progressAlcoholText.text = "0"
                        binding.progressWorkoutOutsideText.text = "0"
                        binding.progressWorkoutInsideText.text = "0"
                        binding.incompleteTaskWater.visibility = View.VISIBLE
                        binding.incompleteTaskOutdoorWorkout.visibility = View.VISIBLE
                        binding.incompleteTaskIndoorWorkout.visibility = View.VISIBLE
                        binding.incompleteTaskBook.visibility = View.VISIBLE
                        binding.incompleteTaskAlcohol.visibility = View.VISIBLE
                        binding.incompleteTaskDiet.visibility = View.VISIBLE
                    } else {
                        waterProgress=it[size - 1].uiWaterProgressTxt.toInt()
                        outsideWorkoutProgress=it[size - 1].uiWorkoutOutProgressTxt.toInt()
                        insideWorkoutProgress=it[size - 1].uiWorkoutInProgressTxt.toInt()
                        dietProgress=it[size - 1].uiMealProgressTxt.toInt()
                        bookProgress=it[size - 1].uiBookPageProgressTxt.toInt()
                        alcoholProgress=it[size - 1].uiAlcoholProgressTxt.toInt()
                        toggleProgressBar(pictureProgress, waterProgress, outsideWorkoutProgress, insideWorkoutProgress, bookProgress, dietProgress, alcoholProgress)
                        visibilityUIIncompleteWater = 0
                        //setUI(size,binding.progressDrinkGallonText,it[size-1].uiWaterProgressTxt)
                        binding.progressDrinkGallonText.text = it[size - 1].uiWaterProgressTxt
                        //setUI(size,binding.progressWorkoutOutsideText,it[size - 1].uiWorkoutOutProgressTxt)
                        binding.progressWorkoutOutsideText.text =
                            it[size - 1].uiWorkoutOutProgressTxt
                        //setUI(size,binding.progressWorkoutInsideText,it[size - 1].uiWorkoutInProgressTxt)
                        binding.progressWorkoutInsideText.text = it[size - 1].uiWorkoutInProgressTxt
                        //setUI(size,binding.progressMealText,it[size - 1].uiMealProgressTxt)
                        binding.progressMealText.text = it[size - 1].uiMealProgressTxt
                        //setUI(size,binding.progressAlcoholText,it[size - 1].uiAlcoholProgressTxt)
                        binding.progressAlcoholText.text = it[size - 1].uiAlcoholProgressTxt
                        //setUI(size,binding.progressBookpageText,it[size - 1].uiBookPageProgressTxt)
                        binding.progressBookpageText.text = it[size - 1].uiBookPageProgressTxt


                        setUiVisibility(
                            5,
                            waterProgress,
                            binding.incompleteTaskWater,
                            binding.completeWaterFull,
                            binding.completeWaterHalf,
                            binding.completeWaterQuarter,
                            binding.completeWater75
                        )
                        setUiVisibility(
                            1,
                            outsideWorkoutProgress,
                            binding.incompleteTaskOutdoorWorkout,
                            binding.completeWorkoutOutsideFull,
                            binding.completeWorkoutOutsideHalf,
                            binding.completeWorkoutOutsideQuarter,
                            binding.completeWorkoutOutside75
                        )
                        setUiVisibility(
                            1,
                            insideWorkoutProgress,
                            binding.incompleteTaskIndoorWorkout,
                            binding.completeWorkoutInsideFull,
                            binding.completeWorkoutInsideHalf,
                            binding.completeWorkoutInsideQuarter,
                            binding.completeWorkoutInside75
                        )
                        setUiVisibility(
                            10,
                            bookProgress,
                            binding.incompleteTaskBook,
                            binding.completebookFull,
                            binding.completebookHalf,
                            binding.completebookQuarter,
                            binding.completebook75
                        )
                         setUiVisibility(1,it[size - 1].uiMealProgressTxt.toInt(),binding.incompleteTaskDiet,binding.completedietFull,binding.completedietHalf,binding.completedietQuarter,binding.completediet75)
                        setUiVisibility(1,it[size - 1].uiAlcoholProgressTxt.toInt(),binding.incompleteTaskAlcohol,binding.completealcoholFull,binding.completealcoholHalf,binding.completealcoholQuarter,binding.completealcohol75)
                        //   Log.d("checking","${it[size - 1].uiMealProgressTxt.toInt()}")

                    }


                }

            )

        }


        executedBefore = true
        isCodeExecuted = true


    }
   private fun takePicture() {
        // Your code to launch the camera activity and take a picture
        // ...

        val contract = registerForActivityResult(ActivityResultContracts.TakePicture()) {
            binding.camPic.setImageURI(null)
            binding.camPic.setImageURI(imageUri)
        }
        imageUri = createImageUri()!!

        contract.launch(imageUri)
       // uploadProgressPic()

        // ...
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRulesBinding.inflate(inflater, container, false)
        toggleProgressBar(
            pictureProgress,
            waterProgress,
            outsideWorkoutProgress,
            insideWorkoutProgress,
            bookProgress,
            dietProgress,
            alcoholProgress
        )
        firebaseAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("UserDailyPhotos")
        val view = binding.root
        val imgView = binding.camPic
        pictureProgress = binding.progressPictureUpdate.text.toString().toInt()
        val contract = registerForActivityResult(ActivityResultContracts.TakePicture()) {
            imgView.setImageURI(null)
            imgView.setImageURI(imageUri)
            uploadProgressPic()
           /* val currentTimestampMillis: Long = System.currentTimeMillis()

// Create a Date object from the timestamp
            val currentDate = Date(currentTimestampMillis)

// Define a date format
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val uid = firebaseAuth.currentUser?.uid
// Format the date as a string
            val formattedDate = dateFormat.format(currentDate)
            val userDailyPhotos = UserDailyPhotos(dateFormat, imageUri)
            if (uid != null) {
                databaseReference.child(uid).setValue(userDailyPhotos).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(requireContext(), "Uploaded", Toast.LENGTH_LONG).show()

                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Failed to Upload details",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }*/
        }
        imageUri = createImageUri()!!
        binding.progressPictureUpdateBtn.setOnClickListener {

            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                // Permission is already granted, you can take a picture
                contract.launch(imageUri)
                //takePicture()
            } else {
                // Permission is not granted, request it from the user
                requestCameraPermission.launch(android.Manifest.permission.CAMERA)
            }
            Toast.makeText(this.context, "Hurray", Toast.LENGTH_SHORT).show()


            binding.completePicFull.visibility = View.VISIBLE
            binding.progressPictureUpdate.text = "1"



        }


        binding.progressWaterBtn.setOnClickListener {

            showCustomDialogBox(
                5,
                binding.incompleteTaskWater,
                binding.completeWaterFull,
                binding.completeWaterHalf,
                binding.completeWaterQuarter,
                binding.completeWater75,
                binding.progressDrinkGallonText,

                )

        }


        binding.progressWorkoutOutsidebtn.setOnClickListener {
            showCustomDialogBox(
                1,
                binding.incompleteTaskOutdoorWorkout,
                binding.completeWorkoutOutsideFull,
                binding.completeWorkoutOutsideHalf,
                binding.completeWorkoutOutsideQuarter,
                binding.completeWorkoutOutside75,
                binding.progressWorkoutOutsideText
            )
        }
        binding.progressWorkoutInsideBtn.setOnClickListener {
            showCustomDialogBox(
                1,
                binding.incompleteTaskIndoorWorkout,
                binding.completeWorkoutInsideFull,
                binding.completeWorkoutInsideHalf,
                binding.completeWorkoutInsideQuarter,
                binding.completeWorkoutInside75,
                binding.progressWorkoutInsideText
            )
        }
        binding.progressBookpageBtn.setOnClickListener {
            showCustomDialogBox(
                10,
                binding.incompleteTaskBook,
                binding.completebookFull,
                binding.completebookHalf,
                binding.completebookHalf,
                binding.completebook75,
                binding.progressBookpageText
            )

        }
        binding.progressMealBtn.setOnClickListener {
            showCustomDialogBox(
                1,
                binding.incompleteTaskDiet,
                binding.completedietFull,
                binding.completedietHalf,
                binding.completedietQuarter,
                binding.completediet75,
                binding.progressMealText
            )

        }
        binding.progressAlcoholBtn.setOnClickListener {
            showCustomDialogBox(
                1,
                binding.incompleteTaskAlcohol,
                binding.completealcoholFull,
                binding.completealcoholHalf,
                binding.completealcoholQuarter,
                binding.completealcohol75,
                binding.progressAlcoholText
            )
        }

        return view


    }
   /* private fun takePic() {
        val takePicIntent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePicIntent,REQUEST_CAMERA)

        }catch (e:ActivityNotFoundException){
            Toast.makeText(requireContext(),"oops"+e.localizedMessage,Toast.LENGTH_SHORT).show()

        }

    }*/

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==REQUEST_CAMERA&&resultCode==RESULT_OK){
            val imgBitmap=data?.extras?.get("data")as Bitmap
            binding.camPic.setImageBitmap(imgBitmap)
        }
        else{
            super.onActivityResult(requestCode, resultCode, data)
        }*/

    var dayCounter = 1
    private  fun uploadProgressPic() {

        val uid = firebaseAuth.currentUser?.uid

        if (uid != null) {
            val storageReference = FirebaseStorage.getInstance().getReference("Users/$uid/")
            val imageUri = createImageUri()


            if (imageUri != null) {
                val imageReference = storageReference.child("Day$dayCounter.jpg")

                imageReference.putFile(imageUri)
                    .addOnSuccessListener {
                        Toast.makeText(activity, "Pic Uploaded", Toast.LENGTH_LONG).show()
                        dayCounter++ // Increment the day counter for the next upload
                    }
                    .addOnFailureListener {
                        Toast.makeText(activity, "FAILED", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(activity, "Image URI is null", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(activity, "User UID is null", Toast.LENGTH_SHORT).show()
        }
       /* val packageName = context?.applicationContext?.packageName


        val uid=firebaseAuth.currentUser?.uid
       // val photoRef=storageReference.child("Users/$uid")
        storageReference = FirebaseStorage.getInstance().getReference("Users/$uid/")

        // Use the imageUri obtained from createImageUri
        imageUri = createImageUri()
        val currentUri=imageUri
        var i=1;
        val imageReference=storageReference.child("Day$i")

        imageReference.putFile(currentUri).addOnSuccessListener {
            Toast.makeText(activity, "Pic Uploaded", Toast.LENGTH_LONG).show()
            i++
        }.addOnFailureListener {
            Toast.makeText(activity, "FAILED", Toast.LENGTH_SHORT).show()
        }
*/

      /*  storageReference.putFile(imageUri).addOnSuccessListener {
            Toast.makeText(activity, "Pic Uploaded", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(activity, "FAILED", Toast.LENGTH_SHORT).show()
        }*/
       /* imageUri = Uri.parse("android.resource://$packageName/${resources.getResourceName(binding.camPic.hashCode())}")
        storageReference= FirebaseStorage.getInstance().getReference("Users/+${firebaseAuth.currentUser?.uid}")
        storageReference.putFile(imageUri).addOnSuccessListener {
            Toast.makeText(activity,"Pic Uploaded",Toast.LENGTH_LONG).show()
        }.addOnFailureListener{
            Toast.makeText(activity,"FAILED",Toast.LENGTH_SHORT).show()
        }*/
    }

    private fun showCustomDialogBox(
        maxValue: Int,
        incomplete: ImageView,
        complete: ImageView,
        half: ImageView,
        quarter: ImageView,
        seventyFive: ImageView,
        labelText: TextView,
    ) {

        val dialogBinding = CustomDialogboxBinding.inflate(LayoutInflater.from(requireContext()))
        val numberPicker = dialogBinding.progressPicker
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogBinding.root)
        numberPicker.maxValue = maxValue
        numberPicker.minValue = 0
        builder.setPositiveButton("Submit") { dialog, which ->
            val selectedValue = numberPicker.value


            // labelText.text=selectedValue.toString()
            labelText.text = selectedValue.toString()
            CoroutineScope(IO).launch {
                database.getUserPrefferedUIDao().insertUIwtrText(
                    UserPrefferedUITxt(
                        0,
                        binding.progressDrinkGallonText.text.toString().trim(),
                        binding.progressWorkoutOutsideText.text.toString().trim(),
                        binding.progressWorkoutInsideText.text.toString().trim(),
                        binding.progressBookpageText.text.toString().trim(),
                        binding.progressMealText.text.toString().trim(),
                        binding.progressAlcoholText.text.toString().trim(),
                        binding.incompleteTaskWater.visibility,
                        binding.completeWaterFull.visibility,
                        binding.completeWater75.visibility,
                        binding.completeWaterHalf.visibility,
                        binding.completeWaterQuarter.visibility,
                        binding.incompleteTaskOutdoorWorkout.visibility,
                        binding.completeWorkoutOutsideFull.visibility,
                        binding.incompleteTaskIndoorWorkout.visibility,
                        binding.completeWorkoutInsideFull.visibility,
                        binding.incompleteTaskBook.visibility,
                        binding.completebookFull.visibility,
                        binding.completebook75.visibility,
                        binding.completebookHalf.visibility,
                        binding.completebookQuarter.visibility,
                        binding.incompleteTaskDiet.visibility,
                        binding.completedietFull.visibility,
                        binding.incompleteTaskAlcohol.visibility,
                        binding.completealcoholFull.visibility

                    )
                )
            }
            Log.d("DB_UPDATE", "updatedwtrText")
            //binding.progressDrinkGallonText.text = selectedValue.toString()

            //viewModel.updateWaterProgress(selectedValue)

        }
        builder.setNegativeButton("Cancel", null)
        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            setUiVisibility(maxValue, newVal, incomplete, complete, half, quarter, seventyFive)
            /*val maxDouble = maxValue.toDouble()
            if (maxValue % 2 == 0) {
                if (newVal == 0) {

                    incomplete.visibility = View.VISIBLE
                    quarter.visibility = View.INVISIBLE
                    half.visibility = View.INVISIBLE
                    seventyFive.visibility = View.INVISIBLE
                    complete.visibility = View.INVISIBLE
                } else if (newVal in (maxValue / 2 - 1)..(maxValue / 2 + 1)) {

                    incomplete.visibility = View.INVISIBLE
                    quarter.visibility = View.INVISIBLE
                    half.visibility = View.VISIBLE
                    seventyFive.visibility = View.INVISIBLE
                    complete.visibility = View.INVISIBLE
                } else if (newVal == maxValue) {

                    incomplete.visibility = View.INVISIBLE
                    quarter.visibility = View.INVISIBLE
                    half.visibility = View.INVISIBLE
                    seventyFive.visibility = View.INVISIBLE
                    complete.visibility = View.VISIBLE
                } else if (newVal.toDouble() in (maxDouble * 0.25 - 0.5)..(maxDouble * 0.25 + 0.5)) {

                    incomplete.visibility = View.INVISIBLE
                    quarter.visibility = View.VISIBLE
                    half.visibility = View.INVISIBLE
                    seventyFive.visibility = View.INVISIBLE
                    complete.visibility = View.INVISIBLE
                } else if (newVal.toDouble() in (maxDouble * 0.75 - 7.5)..(maxDouble * 0.75 + 7.5)) {

                    incomplete.visibility = View.INVISIBLE
                    quarter.visibility = View.INVISIBLE
                    half.visibility = View.INVISIBLE
                    seventyFive.visibility = View.VISIBLE
                    complete.visibility = View.INVISIBLE
                }
            } else {
                if (newVal == 0) {

                    incomplete.visibility = View.VISIBLE
                    quarter.visibility = View.INVISIBLE
                    half.visibility = View.INVISIBLE
                    seventyFive.visibility = View.INVISIBLE
                    complete.visibility = View.INVISIBLE
                } else if (newVal.toDouble() in (maxDouble / 2 - 0.5)..(maxDouble / 2 + 0.5)) {
                    //visibilityUIWater=incomplete.visibility
                    incomplete.visibility = View.INVISIBLE
                    quarter.visibility = View.INVISIBLE
                    half.visibility = View.VISIBLE
                    seventyFive.visibility = View.INVISIBLE
                    complete.visibility = View.INVISIBLE
                } else if (newVal == maxValue) {

                    incomplete.visibility = View.INVISIBLE
                    quarter.visibility = View.INVISIBLE
                    half.visibility = View.INVISIBLE
                    seventyFive.visibility = View.INVISIBLE
                    complete.visibility = View.VISIBLE
                } else if (newVal.toDouble() in (maxDouble * 0.25 - 0.5)..(maxDouble * 0.25 + 0.5)) {

                    incomplete.visibility = View.INVISIBLE
                    quarter.visibility = View.VISIBLE
                    half.visibility = View.INVISIBLE
                    seventyFive.visibility = View.INVISIBLE
                    complete.visibility = View.INVISIBLE
                } else if (newVal.toDouble() in (maxDouble * 0.75 - 7.5)..(maxDouble * 0.75 + 7.5)) {

                    incomplete.visibility = View.INVISIBLE
                    quarter.visibility = View.INVISIBLE
                    half.visibility = View.INVISIBLE
                    seventyFive.visibility = View.VISIBLE
                    complete.visibility = View.INVISIBLE
                }
            }*/

        }


        val dialog = builder.create()
        dialog.show()


    }


    private fun createImageUri(): Uri {
        // we have passed our application's context and access the files directory of our application
        val image = File(requireContext().applicationContext.filesDir, "camera_photo.png")
        return FileProvider.getUriForFile(
            requireContext().applicationContext,
            "com.example.a75hardchallenge.fileProvider",
            image
        )


    }

    fun toggleProgressBar(pictureProgress:Int,waterProgress:Int,outsideWorkoutProgress:Int,insideWorkoutProgress:Int,bookProgress:Int,dietProgress:Int,alcoholProgress:Int){
        val total=70
        val currentTotal=(pictureProgress*10)+(waterProgress*2)+(outsideWorkoutProgress*10)+(insideWorkoutProgress*10)+bookProgress+(dietProgress*10)+(alcoholProgress*10)
        Log.d("CurrentTotal","$currentTotal")
       val currentProgress =  (currentTotal.toFloat() / total)*100
        //val currentProgress=100
        //val check=33.165
        Log.d("CurrentProgress","$currentProgress")
        val progressNearestTen= round(currentProgress).toInt()
        val finalCounter=progressNearestTen
        manipulateUi(finalCounter)
    }

    private fun manipulateUi(finalCounter: Int) {
        binding.progressBar.progress=finalCounter
        Log.d("progress","$finalCounter")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


