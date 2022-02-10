# Kotlin BitArray

A minimal BitArray implementation for Kotlin, which aims to save booleans memory-efficiently.  
It uses ByteArray to save bits, so the size of allocated memory is `(size-1).floorDiv(8)+1)`.

## Usecase
 - Setting and saving bunch of flags
 - ~~Useless~~
