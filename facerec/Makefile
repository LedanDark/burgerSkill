DLIB = dlib-19.8

OS = $(shell uname)
ifeq ($(OS), Darwin)
	SLEXT	= dylib

else
	LDFLAGS = -L$(DLIB)/dlib/external/libjpeg -L$(DLIB)/dlib/external/libpng -static-libgcc -static-libstdc++
	LIBS = -lws2_32 -lwinmm -ljpeg
	SLEXT	= dll
	XINC = -I$(DLIB)/dlib/external/libjpeg -I$(DLIB)/dlib/external/libpng
endif

LIBS += -static -lpthread

dynamic:
	g++ -m64 -shared -std=c++11 -O3 -DDLIB_NO_GUI_SUPPORT -DDLIB_JPEG_SUPPORT -I$(DLIB) $(XINC) $(DLIB)/dlib/all/source.cpp $(LDFLAGS) facerec.cpp -o facerec.$(SLEXT) $(LIBS)

test:
	gcc facerec_test.c facerec.dll -o facerec_test.exe


imglibs:
	cd $(DLIB)/dlib/external/libjpeg; ls *.cpp | xargs -I{} g++ -c {}; ar cr libjpeg.a *.o
	cd $(DLIB)/dlib/external/libpng; ls *.c | xargs -I{} gcc -c {}; ar cr libpng.a *.o
