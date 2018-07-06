/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 1.3.28
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdljava.x.swig;

public class SDL_Overlay {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected SDL_Overlay(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SDL_Overlay obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public void delete() {
    if(swigCPtr != 0 && swigCMemOwn) {
      swigCMemOwn = false;
      SWIG_SDLVideoJNI.delete_SDL_Overlay(swigCPtr);
    }
    swigCPtr = 0;
  }

  public void setFormat(long value) {
    SWIG_SDLVideoJNI.SDL_Overlay_format_set(swigCPtr, value);
  }

  public long getFormat() {
    return SWIG_SDLVideoJNI.SDL_Overlay_format_get(swigCPtr);
  }

  public void setW(int value) {
    SWIG_SDLVideoJNI.SDL_Overlay_w_set(swigCPtr, value);
  }

  public int getW() {
    return SWIG_SDLVideoJNI.SDL_Overlay_w_get(swigCPtr);
  }

  public void setH(int value) {
    SWIG_SDLVideoJNI.SDL_Overlay_h_set(swigCPtr, value);
  }

  public int getH() {
    return SWIG_SDLVideoJNI.SDL_Overlay_h_get(swigCPtr);
  }

  public void setPlanes(int value) {
    SWIG_SDLVideoJNI.SDL_Overlay_planes_set(swigCPtr, value);
  }

  public int getPlanes() {
    return SWIG_SDLVideoJNI.SDL_Overlay_planes_get(swigCPtr);
  }

  public void setPitches(SWIGTYPE_p_unsigned_short value) {
    SWIG_SDLVideoJNI.SDL_Overlay_pitches_set(swigCPtr, SWIGTYPE_p_unsigned_short.getCPtr(value));
  }

  public SWIGTYPE_p_unsigned_short getPitches() {
    long cPtr = SWIG_SDLVideoJNI.SDL_Overlay_pitches_get(swigCPtr);
    return (cPtr == 0) ? null : new SWIGTYPE_p_unsigned_short(cPtr, false);
  }

  public void setPixels(SWIGTYPE_p_p_unsigned_char value) {
    SWIG_SDLVideoJNI.SDL_Overlay_pixels_set(swigCPtr, SWIGTYPE_p_p_unsigned_char.getCPtr(value));
  }

  public SWIGTYPE_p_p_unsigned_char getPixels() {
    long cPtr = SWIG_SDLVideoJNI.SDL_Overlay_pixels_get(swigCPtr);
    return (cPtr == 0) ? null : new SWIGTYPE_p_p_unsigned_char(cPtr, false);
  }

  public void setHwfuncs(SWIGTYPE_p_private_yuvhwfuncs value) {
    SWIG_SDLVideoJNI.SDL_Overlay_hwfuncs_set(swigCPtr, SWIGTYPE_p_private_yuvhwfuncs.getCPtr(value));
  }

  public SWIGTYPE_p_private_yuvhwfuncs getHwfuncs() {
    long cPtr = SWIG_SDLVideoJNI.SDL_Overlay_hwfuncs_get(swigCPtr);
    return (cPtr == 0) ? null : new SWIGTYPE_p_private_yuvhwfuncs(cPtr, false);
  }

  public void setHwdata(SWIGTYPE_p_private_yuvhwdata value) {
    SWIG_SDLVideoJNI.SDL_Overlay_hwdata_set(swigCPtr, SWIGTYPE_p_private_yuvhwdata.getCPtr(value));
  }

  public SWIGTYPE_p_private_yuvhwdata getHwdata() {
    long cPtr = SWIG_SDLVideoJNI.SDL_Overlay_hwdata_get(swigCPtr);
    return (cPtr == 0) ? null : new SWIGTYPE_p_private_yuvhwdata(cPtr, false);
  }

  public void setHw_overlay(long value) {
    SWIG_SDLVideoJNI.SDL_Overlay_hw_overlay_set(swigCPtr, value);
  }

  public long getHw_overlay() {
    return SWIG_SDLVideoJNI.SDL_Overlay_hw_overlay_get(swigCPtr);
  }

  public void setUnusedBits(long value) {
    SWIG_SDLVideoJNI.SDL_Overlay_UnusedBits_set(swigCPtr, value);
  }

  public long getUnusedBits() {
    return SWIG_SDLVideoJNI.SDL_Overlay_UnusedBits_get(swigCPtr);
  }

  public SDL_Overlay() {
    this(SWIG_SDLVideoJNI.new_SDL_Overlay(), true);
  }

}