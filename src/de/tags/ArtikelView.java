package de.tags;

import java.io.IOException;
import javax.servlet.jsp.tagext.TagSupport;

import de.classes.Hose;
import de.classes.Produkt;
import de.classes.Schuhe;
import de.classes.Shirt;
import de.utilities.ReadFromFile;

public class ArtikelView extends TagSupport {
	private static final long serialVersionUID = 1L;
	private Produkt produkt;
    public static int counter = 0;
	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}

	public int doStartTag() {
		try {
			pageContext.getOut().append(getArtikelView());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	private String getArtikelView() throws IOException {
		String artikelview = ReadFromFile.readContentFromFile(pageContext, "artikel.html");
		String counteralsString = Integer.toString(counter) ;
		
		artikelview = artikelview.replace("PLATZHALTER0", counteralsString);
		artikelview = artikelview.replace("PLATZHALTER1", produkt.getName());
		artikelview = artikelview.replace("PLATZHALTER2", produkt.getBeschreibung());
		artikelview = artikelview.replace("PLATZHALTER3", String.valueOf(produkt.getProdukt_id()));
		artikelview = artikelview.replace("PLATZHALTER4", String.valueOf(produkt.getPreis()));

		if (produkt instanceof Shirt) {
			artikelview = artikelview.replace("PLATZHALTERGROESSE1", "<option value=S>S</option>");
			artikelview = artikelview.replace("PLATZHALTERGROESSE2", "<option value=2>M</option>");
			artikelview = artikelview.replace("PLATZHALTERGROESSE3", "<option value=3>L</option>");
			artikelview = artikelview.replace("PLATZHALTERGROESSE4", "<option value=4>XL</option>");
			artikelview = artikelview.replace("PLATZHALTERGROESSE5", "<option value=5>XXL</option>");
			artikelview = artikelview.replace("PLATZHALTERGROESSE6", "");
			artikelview = artikelview.replace("PLATZHALTERGROESSE7", "");
			artikelview = artikelview.replace("PLATZHALTERGROESSE8", "");
			artikelview = artikelview.replace("PLATZHALTERGROESSE9", "");
			System.out.println("test1");
		}
		if (produkt instanceof Schuhe) {
			artikelview = artikelview.replace("PLATZHALTERGROESSE1", "<option value=38>38</option>");
			artikelview = artikelview.replace("PLATZHALTERGROESSE2", "<option value=39>39</option>");
			artikelview = artikelview.replace("PLATZHALTERGROESSE3", "<option value=40>40</option>");
			artikelview = artikelview.replace("PLATZHALTERGROESSE4", "<option value=41>41</option>");
			artikelview = artikelview.replace("PLATZHALTERGROESSE5", "<option value=42>42</option>");
			artikelview = artikelview.replace("PLATZHALTERGROESSE6", "<option value=43>43</option>");
			artikelview = artikelview.replace("PLATZHALTERGROESSE7", "<option value=44>44</option>");
			artikelview = artikelview.replace("PLATZHALTERGROESSE8", "<option value=45>45</option>");
			artikelview = artikelview.replace("PLATZHALTERGROESSE9", "<option value=46>46</option>");
			System.out.println("test11");
		}
		if (produkt instanceof Hose) {
			artikelview = artikelview.replace("PLATZHALTERGROESSE1", "<option value=28>28</option>");
			artikelview = artikelview.replace("PLATZHALTERGROESSE2", "<option value=30>30</option>");
			artikelview = artikelview.replace("PLATZHALTERGROESSE3", "<option value=32>32</option>");
			artikelview = artikelview.replace("PLATZHALTERGROESSE4", "<option value=34>34</option>");
			artikelview = artikelview.replace("PLATZHALTERGROESSE5", "<option value=36>36</option>");
			artikelview = artikelview.replace("PLATZHALTERGROESSE6", "<option value=38>38</option>");
			artikelview = artikelview.replace("PLATZHALTERGROESSE7", "<option value=40>40</option>");
			artikelview = artikelview.replace("PLATZHALTERGROESSE8", "<option value=42>42</option>");
			artikelview = artikelview.replace("PLATZHALTERGROESSE9", "");
			System.out.println("test111");
		}
		System.out.println(counter);
		counter++;
		return artikelview;
	}

}
