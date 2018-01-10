#ifndef FACEREC_H
#define FACEREC_H

#ifdef WIN32
#  define FACERECBAPI __declspec(dllexport)
#else
#  define FACERECAPI
#endif

#ifdef __cplusplus
extern "C" {
#endif
#define EMBEDDINGSIZE 128 // don't change this please

FACERECAPI void facerec_init(char* network, char* predictor);

FACERECAPI void facerec_push_image_file(char* filename);

FACERECAPI int facerec_get_num_faces();

FACERECAPI void facerec_get_embedding(int faceid, float* embedding);


#ifdef __cplusplus
}
#endif
#endif // FACEREC_H
