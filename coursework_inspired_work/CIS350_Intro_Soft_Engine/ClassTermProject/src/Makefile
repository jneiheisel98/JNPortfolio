TARGETS:= example_mti_receive_data example_mti_parse_logfile
OBJLIBS	= xspublic
INCLUDE=-I. -Ixspublic
CFLAGS=-g $(INCLUDE)
CXXFLAGS=-std=c++11 $(CFLAGS)
LFLAGS=-Lxspublic/xscontroller -Lxspublic/xscommon -Lxspublic/xstypes -lxscontroller -lxscommon -lxstypes -lpthread -lrt -ldl
LCDDOCS:= LCD
LCDLINK=-lwiringPi

.PHONY: $(OBJLIBS)
all : $(OBJLIBS) $(TARGETS) $(LCDDOCS)

xspublic :
	$(MAKE) -C xspublic $(MFLAGS)

example_mti_receive_data: example_mti_receive_data.cpp.o
example_mti_parse_logfile: example_mti_parse_logfile.cpp.o

$(TARGETS):
	$(CXX) $(CFLAGS) $(INCLUDE) $^ $(LCDDOCS) -o $@ $(LFLAGS) $(LCDLINK)

LCD: LCD.cpp
$(LCDDOCS):
	  $(CXX) -c  $^ -o $@ $(LCDLINK)
	


%.cpp.o: %.cpp
	$(CXX) -c $(CXXFLAGS) $< -o $@

clean :
	-$(RM) $(OBJECTS) $(TARGETS)
	-$(RM) *.o
	-$(MAKE) -C xspublic/xscontroller $(MFLAGS) clean
	-$(MAKE) -C xspublic/xscommon $(MFLAGS) clean

