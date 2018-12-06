cmake_minimum_required(VERSION 3.4.1)

add_library(Crash-lib SHARED crash.cpp)
add_subdirectory(breakpad)