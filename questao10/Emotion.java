package avaliacao2.questao10;


public class Emotion {

	private Integer id;
	private String emocao;
	
	private Integer countFeliz;
	private Integer countTriste;
	private String feliz = ":-\\)";
	private String triste = ":-\\(";
	
	public Emotion() {
	}
	
	public Emotion(String emocao) {
		this.emocao = emocao;
	}

	public void selecEmotion(String s) {
		this.countFeliz = s.split(this.feliz, -1).length - 1;
		this.countTriste = s.split(this.triste, -1).length - 1;
		resultEmotion();
	}

	public void resultEmotion() {
		if (getCountFeliz() > getCountTriste()) {
			String  emo = "Divertido!";
			System.out.println(emo);
			setEmocao(emo);
		} else if (getCountFeliz() < getCountTriste()) {
			String  emo = "Chateado!"; 
			System.out.println(emo);
			setEmocao(emo);
		} else {
			String  emo = "Neutro!";
			System.out.println(emo);
			setEmocao(emo);
		}
	}

	public Integer getCountFeliz() {
		return countFeliz;
	}

	public Integer getCountTriste() {
		return countTriste;
	}

	public String getEmocao() {
		return emocao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setEmocao(String emocao) {
		this.emocao = emocao;
	}
	
}
