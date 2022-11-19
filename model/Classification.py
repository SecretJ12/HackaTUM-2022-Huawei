from keras.models import load_model
from tensorflow.keras.utils import load_img

from tensorflow.keras.utils import img_to_array
from keras.applications.vgg16 import preprocess_input
from keras.applications.vgg16 import decode_predictions
from keras.applications.vgg16 import VGG16
import numpy as np

from keras.models import load_model

model = load_model('model_saved.h5')

image = load_img('/home/oliver/coding/py/HackaTUM_Data/dataset/hackatum_dataset/val/primary/142360954539412.jpg', target_size=(224, 224))
img = np.array(image)
img = img / 255.0
img = img.reshape(1,224,224,3)
label = model.predict(img)
print("Predicted Class (0 - footway , 1- primary): ", label[0][0])

