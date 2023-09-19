package com.anypli.roadtriip.ui.event
import CameraViewModel
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.anypli.roadtriip.R
import com.anypli.roadtriip.base.BaseScreen
import com.anypli.roadtriip.ui.main.AppNavigator
import com.anypli.roadtriip.ui.shared.theme.RoadtriipTheme

var uri : Uri?= null

@Composable
fun CameraScreen(
    viewModel: CameraViewModel ,
    navigator: AppNavigator
) {
    val snackbarHostState = remember { SnackbarHostState() }

    BaseScreen(
        viewModel = viewModel ,
        navigationCallback = {} ,
        snackbarHostState = snackbarHostState
    ) { modifier ->
        CameraScreen(
            snackbarHostState = snackbarHostState,
            modifier = modifier ,
            onNextClicked ={
                viewModel.onNextClicked()
            },
            onCameraIconClicked={

            }
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CameraScreen(
    snackbarHostState : SnackbarHostState = remember { SnackbarHostState() } ,
    modifier: Modifier ,
    onNextClicked: () -> Unit ,
    onCameraIconClicked: () -> Unit
    ){
    Image(
        painter = painterResource(id = R.drawable.ic_shape) , contentDescription = "" ,
        modifier = Modifier.fillMaxSize() , contentScale = ContentScale.FillBounds
    )
    var galleryPickerVisible by remember { mutableStateOf(false)}


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val cameraPermissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                onCameraIconClicked()
            } else {

            }
        }
        IconButton(
            onClick = {
                // Avant de lancer la capture de photo, demandez la permission de la caméra
                cameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
                galleryPickerVisible = true // Activer la visibilité de la galerie
            },
            modifier = Modifier.size(dimensionResource(R.dimen.dimen_global_48))
        ) {
            Icon(Icons.Default.CameraAlt, contentDescription = stringResource(R.string.camera_icon))
        }

        // Afficher le contenu de PickImageFromGallery lorsque galleryPickerVisible est vrai
        if (galleryPickerVisible) {
            PickImageFromGallery(onImageSelected = { selectedUri ->
               uri = selectedUri
                galleryPickerVisible = false
            })
        }
        AsyncImage(
            model = uri,
            contentDescription = null,
        )
//        Image(
//            painter = painterResource(R.drawable.image_add),
//            //painter = painterResource(R.drawable.add_image),
//            contentDescription = "image_add",
//            modifier = Modifier
//                .padding(vertical = dimensionResource(R.dimen.margin_global_16dp))
//                .size(dimensionResource(R.dimen.dimen_global_240)) // Augmenter la taille de l'image
//        )
        Button(
            onClick = {
                onNextClicked()
            },
            colors = ButtonDefaults.buttonColors(RoadtriipTheme.colors.yeloowsc),
            modifier = Modifier
                .padding(horizontal = dimensionResource(R.dimen.margin_global_16dp))
                .height(dimensionResource(R.dimen.dimen_global_48)) // Augmenter la hauteur du bouton
        ) {
            Text(text = stringResource(R.string.suivant))
        }
    }

    }

@Composable
fun PickImageFromGallery(onImageSelected: (Uri) -> Unit) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    var galleryPickerVisible by remember { mutableStateOf(false) }

    val galleryLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imageUri = uri
            onImageSelected(uri)
        }
    }
    val cameraLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicture()) { success ->
            if (success) {

            }
        }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        imageUri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }
            bitmap.value?.let { btm ->
                Image(
                    bitmap = btm.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(400.dp)
                        .padding(20.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            androidx.compose.material.Button(
                onClick = { galleryLauncher.launch("image/*") },
                modifier = Modifier.weight(1f)
            ) {
                androidx.compose.material.Text(text = "Pick Image")
            }
            Button(
                onClick = {
                    galleryPickerVisible = false // Assurez-vous que la galerie est désactivée
                    // Créez un Uri factice pour l'appel de la fonction launch
                    val dummyUri: Uri? = Uri.parse("content://dummy")
                    cameraLauncher.launch(dummyUri)
                },
                modifier = Modifier.weight(1f)
            ) {
                androidx.compose.material.Text(text = "Take Photo")
            }
        }
    }
}
@Preview
@Composable
fun CameraScreenPreview() {
    CameraScreen(
        modifier= Modifier ,
        onNextClicked ={} ,
        onCameraIconClicked ={}



    )
}





