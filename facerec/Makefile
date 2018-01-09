DLIB = dlib-19.8


all:
		g++ -shared -std=c++11 -O3 -DDLIB_NO_GUI_SUPPORT -I$(DLIB) $(DLIB)/dlib/all/source.cpp -lpthread facerec.cpp -o facerec.dylib
		#g++ -std=c++11 -O3 -I/opt/X11/include -I$(DLIB) $(DLIB)/dlib/all/source.cpp -lpthread dnn_face_recognition_ex.cpp -L/opt/X11/lib -lX11
	#g++ -std=c++11 -O3 -I.. $(DLIB)/dlib/all/source.cpp -lpthread facerec.cpp
#	g++ -std=c++11 -O3 -I/opt/X11/include -I.. ../dlib/all/source.cpp -lpthread dnn_face_recognition_ex.cpp -L/opt/X11/lib -lX11
