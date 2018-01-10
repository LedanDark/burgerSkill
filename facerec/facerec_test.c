#undef WIN32
#include "facerec.h"
#include <stdio.h>

int main() {

  facerec_init("data/dlib_face_recognition_resnet_model_v1.dat", "data/shape_predictor_5_face_landmarks.dat");

  facerec_push_image_file("data/bald_guys.jpg");

  int nfaces = facerec_get_num_faces();
  
  printf("nfaces = %d\n", nfaces);

}
