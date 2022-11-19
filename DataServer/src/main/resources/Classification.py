from keras.models import load_model
from tensorflow.keras.utils import load_img

from tensorflow.keras.utils import img_to_array
from keras.applications.vgg16 import preprocess_input
from keras.applications.vgg16 import decode_predictions
from keras.applications.vgg16 import VGG16
import numpy as np

from keras.models import load_model

import sys

def main(argv):
    path_to_image = argv[0]

    model = load_model('model_saved.h5')

    image = load_img(path_to_image, target_size=(224, 224))
    img = np.array(image)
    img = img / 255.0
    img = img.reshape(1,224,224,3)
    label = model.predict(img)
    print(f"Found classification: {label[0][0]}"")



if __name__ == "__main__":
    main(sys.argv[1:])
