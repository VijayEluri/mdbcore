package uk.co.brotherlogic.mdb;

import java.util.Collection;

import junit.framework.TestCase;
import uk.co.brotherlogic.mdb.artist.Artist;
import uk.co.brotherlogic.mdb.parsers.DiscogParser;
import uk.co.brotherlogic.mdb.record.Record;
import uk.co.brotherlogic.mdb.record.Track;

/**
 * Testing the discogs parser
 * 
 * @author simon
 * 
 */
public class DiscogTest extends TestCase
{

   /** Double A Side */
   private static final int DISCOG_DOUBLE = 2958490;

   /** The Fall - Wonderful and Frightening World Box Set */
   private static final int DISCOG_FALL = 2518468;

   /** MV & EE - Drone Trailer */
   private static final int DISCOG_NUMBER = 1642454;

   /** Marissa Nadler - Covers Vol 1 */
   private static final int DISCOG_NUMBER_2 = 2411995;

   /** V7W - Firefly Dusk */
   private static final int DISCOG_NUMBER_PERS = 759539;

   /** Turkish Freakout */
   private static final int DISCOG_TURKISH = 2376631;

   /** Turkish Freakout */
   private static final int DISCOG_VOSW = 2391525;

   /** Marissa Nadler - Marissa Nadler */
   private static final int MARISSA_NADLER = 2913124;

   /**
    * Constructor
    */
   public DiscogTest()
   {
      super();
      Connect.setForDevMode();
   }

   /**
    * Main test method
    */
   public final void testDiscog()
   {
      DiscogParser parser = new DiscogParser();

      // This is MV & EE - Drone Trailer
      try
      {
         Record r = parser.parseDiscogRelease(DISCOG_NUMBER);

         // Check that the format tracks are correct
         assert (r.getTracks().iterator().next().getFormTrackNumber() != -1);
         assert (r.getDiscogsNum() == DISCOG_NUMBER);

      }
      catch (Exception e)
      {
         e.printStackTrace();
         assert (false);
      }
   }

   public final void testDiscogFormatTracks()
   {
      DiscogParser parser = new DiscogParser();

      // This is MV & EE - Drone Trailer
      try
      {
         Record r = parser.parseDiscogRelease(DISCOG_NUMBER_2);

         // Check that the format tracks are correct
         assert (r.getTracks().iterator().next().getFormTrackNumber() != -1);

      }
      catch (Exception e)
      {
         e.printStackTrace();
         assert (false);
      }
   }

   public final void testDiscogLabels()
   {
      DiscogParser parser = new DiscogParser();

      // This is MV & EE - Drone Trailer
      try
      {
         Record r = parser.parseDiscogRelease(DISCOG_NUMBER);

         // Check that the format tracks are correct
         assert (r.getLabels().size() == 1);
         assert (r.getLabels().iterator().next().getName().equals("Dicristina Stair Builders"));

      }
      catch (Exception e)
      {
         e.printStackTrace();
         assert (false);
      }
   }

   public final void testDiscogPersonnel()
   {
      DiscogParser parser = new DiscogParser();
      try
      {
         Record r = parser.parseDiscogRelease(DISCOG_NUMBER_PERS);

         // Get the first track
         Track t = r.getTrack(1);
         Collection<Artist> pers = t.getPersonnel();

         // Should be 2
         assert (pers.size() == 2);

         for (Artist art : pers)
            assert ((art.getShowName().equals("Paul Blakesley")) || (art.getShowName()
                  .equals("Chris Walmsley")));
      }
      catch (Exception e)
      {
         e.printStackTrace();
         assert (false);
      }
   }

   public final void testDiscogTurkish()
   {

      DiscogParser parser = new DiscogParser();

      // This is the turkish record
      try
      {
         Record r = parser.parseDiscogRelease(DISCOG_TURKISH);

         // Check that the format tracks are correct
         assert (r.getTracks().size() == 18);
         System.err.println("GOT " + r.getTitle());

      }
      catch (Exception e)
      {
         e.printStackTrace();
         assert (false);
      }
   }

   public final void testDiscogVots()
   {

      DiscogParser parser = new DiscogParser();

      // This is the turkish record
      try
      {
         Record r = parser.parseDiscogRelease(DISCOG_VOSW);

         // Check that the format tracks are correct
         assert (r.getTracks().size() == 5);
      }
      catch (Exception e)
      {
         e.printStackTrace();
         assert (false);
      }
   }

   /**
    * Main test method
    */
   public final void testDoubleASide()
   {
      DiscogParser parser = new DiscogParser();

      try
      {
         Record r = parser.parseDiscogRelease(DISCOG_DOUBLE);

         // Check that the format tracks are correct
         assert (r.getTracks().size() == 2);

      }
      catch (Exception e)
      {
         e.printStackTrace();
         assert (false);
      }
   }

   /**
    * Fall Test
    */
   public final void testFall()
   {
      DiscogParser parser = new DiscogParser();

      try
      {
         Record r = parser.parseDiscogRelease(DISCOG_FALL);
         System.err.println("TRACKS = " + r.getTracks().size());
         assert (r.getTracks().size() == 50);

         // Check the last track has a correct title
         for (Track t : r.getTracks())
            if (t.getTrackNumber() == 50)
               assert (t.getTitle().equals("Middle Mass (Live)"));

      }
      catch (Exception e)
      {
         e.printStackTrace();
         assert (false);
      }
   }

   /**
    * Check on the artist author retrieve
    */
   public final void testNadler()
   {
      DiscogParser parser = new DiscogParser();

      // This is MV & EE - Drone Trailer
      try
      {
         Record r = parser.parseDiscogRelease(MARISSA_NADLER);

         System.err.println("NADLER = " + r.getAuthor());
         // Check that the format tracks are correct
         assert (r.getAuthor().equals("Nadler, Marissa"));

      }
      catch (Exception e)
      {
         e.printStackTrace();
         assert (false);
      }
   }
}
