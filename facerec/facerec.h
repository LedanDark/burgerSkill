#ifndef FACEREC_H
#define FACEREC_H

#ifdef WIN32
#  define DSPLIBAPI __declspec(dllexport)
#else
#  define DSPLIBAPI
#endif

#ifdef __cplusplus
extern "C" {
#endif
#define EMBEDDINGSIZE 128 // don't change this please

void facerec_init(char* network, char* predictor);

void facerec_push_image_file(char* filename);

int facerec_get_num_faces();

void facerec_get_embedding(int faceid, float* embedding);


#ifdef __cplusplus
}
#endif
#endif // FACEREC_H
