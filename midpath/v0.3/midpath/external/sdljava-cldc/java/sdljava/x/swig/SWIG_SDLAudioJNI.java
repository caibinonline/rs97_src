/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version: 1.3.22
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdljava.x.swig;

class SWIG_SDLAudioJNI {
  public final static native void set_SDL_AudioSpec_freq(long jarg1, int jarg2);
  public final static native int get_SDL_AudioSpec_freq(long jarg1);
  public final static native void set_SDL_AudioSpec_format(long jarg1, int jarg2);
  public final static native int get_SDL_AudioSpec_format(long jarg1);
  public final static native void set_SDL_AudioSpec_channels(long jarg1, short jarg2);
  public final static native short get_SDL_AudioSpec_channels(long jarg1);
  public final static native void set_SDL_AudioSpec_silence(long jarg1, short jarg2);
  public final static native short get_SDL_AudioSpec_silence(long jarg1);
  public final static native void set_SDL_AudioSpec_samples(long jarg1, int jarg2);
  public final static native int get_SDL_AudioSpec_samples(long jarg1);
  public final static native void set_SDL_AudioSpec_padding(long jarg1, int jarg2);
  public final static native int get_SDL_AudioSpec_padding(long jarg1);
  public final static native void set_SDL_AudioSpec_size(long jarg1, long jarg2);
  public final static native long get_SDL_AudioSpec_size(long jarg1);
  public final static native void set_SDL_AudioSpec_callback(long jarg1, long jarg2);
  public final static native long get_SDL_AudioSpec_callback(long jarg1);
  public final static native void set_SDL_AudioSpec_userdata(long jarg1, long jarg2);
  public final static native long get_SDL_AudioSpec_userdata(long jarg1);
  public final static native long new_SDL_AudioSpec();
  public final static native void delete_SDL_AudioSpec(long jarg1);
  public final static native int SWIG_SDL_OpenAudio(long jarg1, long jarg2);
  public final static native void SDL_PauseAudio(int jarg1);
}